package com.hanatour.hats.test.plugins.autoparams;

import autoparams.customization.Customizer;
import autoparams.generator.ObjectContainer;
import autoparams.generator.ObjectGenerationContext;
import autoparams.generator.ObjectGenerator;
import autoparams.generator.ObjectQuery;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lombok builder 패턴에 맞게 값을 넣어주는데 Entity에서는 실제 DB 필드 사이즈가 넘어가면 에러가 발생하기 때문에
 * 대응하는 코드가 필요해서 만들었습니다.
 */
public class EntityBuilderCustomizer implements Customizer {

    private final String builderMethodName;
    private final String buildMethodName;

    public EntityBuilderCustomizer() {
        this("builder", "build");
    }

    protected EntityBuilderCustomizer(String builderMethodName, String buildMethodName) {
        this.builderMethodName = builderMethodName;
        this.buildMethodName = buildMethodName;
    }

    @Override
    public ObjectGenerator customize(ObjectGenerator generator) {

        return (query, context) -> getEntityBuilder(query.getType())
                .map(builder -> factory(((Class<?>) query.getType()), builder, context))
                .orElseGet(() -> generator.generate(query, context));
    }

    private Optional<Object> getEntityBuilder(Type type) {
        return type instanceof Class<?>
                ? getEntityBuilder((Class<?>) type)
                : Optional.empty();
    }

    private Optional<Object> getEntityBuilder(Class<?> type) {
        if (type.getAnnotation(Entity.class) == null) {
            return Optional.empty();
        }
        return Arrays
                .stream(type.getDeclaredMethods())
                .filter(method -> method.getName().equals(builderMethodName))
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .map(method -> invoke(null, method))
                .findFirst();
    }

    private ObjectContainer factory(Class<?> entityClass, Object builder, ObjectGenerationContext context) {
        setProperties(entityClass, builder, context);
        return buildObject(builder);
    }

    private static void setProperties(Class<?> entityClass, Object builder, ObjectGenerationContext context) {
        Arrays
                .stream(builder.getClass().getDeclaredMethods())
                .filter(method -> method.getParameterCount() == 1)
                .forEach(setter -> setProperty(entityClass, builder, setter, context));
    }

    private static void setProperty(
            Class<?> entityClass,
            Object builder,
            Method setter,
            ObjectGenerationContext context
    ) {
        ObjectQuery query = () -> setter.getGenericParameterTypes()[0];
        Object argument = generateArgumentByEntity(entityClass, setter, context, query);
        invoke(builder, setter, argument);
    }

    private ObjectContainer buildObject(Object builder) {
        try {
            Method build = builder.getClass().getMethod(buildMethodName);
            return new ObjectContainer(invoke(builder, build));
        } catch (NoSuchMethodException | SecurityException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (IllegalAccessException
                 | IllegalArgumentException
                 | InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static Object generateArgumentByEntity(final Class<?> entityClass,
                                                   final Method setter,
                                                   final ObjectGenerationContext context,
                                                   final ObjectQuery query) {

        final Optional<Field> fieldOptional = getField(entityClass, setter.getName());
        if (fieldOptional.isEmpty()) {
            return context.generate(query);
        }
        final Field field = fieldOptional.get();
        // 재귀호출 방지 및 순환참조 오류가 날수 있는 객체는 null 리턴
        if (isEntityRelation(entityClass, field)) {
            return null;
        }

        switch (field.getType().getSimpleName()) {
            case "String":
                return new StringColumnGenerator().generate(entityClass, field, context, query);
            case "Long":
                return new LongColumnGenerator().generate(entityClass, field, context, query);
            case "Integer":
                return new IntegerColumnGenerator().generate(entityClass, field, context, query);
            default:
                return context.generate(query);
        }
    }

    private static boolean isEntityRelation(Class<?> entityClass, Field field) {
        if (field.getType().equals(entityClass)) {
            return true;
        }
        if (field.getAnnotation(ManyToOne.class) != null) {
            return true;
        }
        if (field.getAnnotation(OneToMany.class) != null && field.getAnnotation(OneToMany.class).mappedBy().equals("")) {
            return true;
        }
        if (field.getAnnotation(OneToOne.class) != null && field.getAnnotation(OneToOne.class).mappedBy().equals("")) {
            return true;
        }
        return false;
    }

    private static Optional<Field> getField(Class<?> entityClass, String fieldName) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> field.getName().equals(fieldName))
                .findFirst();
    }

    interface ColumnGenerator {
        Object generate(Class<?> entityClass, Field field, ObjectGenerationContext context, ObjectQuery query);
        default boolean isId(Field field) {
            return field.getDeclaredAnnotation(Id.class) != null;
        }
    }

    static class StringColumnGenerator implements ColumnGenerator {

        private static final Pattern REGEX_LENGTH = Pattern.compile("char\\(\\s*(\\d+)\\s*\\)", Pattern.CASE_INSENSITIVE);

        @Override
        public Object generate(Class<?> entityClass, Field field, ObjectGenerationContext context, ObjectQuery query) {
            if (isId(field)) {
                return null;
            }

            final Column column = field.getDeclaredAnnotation(Column.class);
            if (column != null && column.columnDefinition().length() > 0) {
                return getColumnDefinitionValue(column.columnDefinition(), context, query);
            }

            if (column != null) {
                return getColumnLengthValue(column.length(), context, query);
            }

            return getDefaultColumnValue(context, query);
        }

        private String getDefaultColumnValue(ObjectGenerationContext context, ObjectQuery query) {
            final String value = String.class.cast(context.generate(query));
            final int length = value.length() > 255 ? 255 : value.length();
            return value.substring(0, length);
        }

        private String getColumnLengthValue(int columnLength, ObjectGenerationContext context, ObjectQuery query) {
            final String value = String.class.cast(context.generate(query));
            final int length = value.length() > columnLength ? columnLength : value.length();
            return value.substring(0, length);
        }

        private String getColumnDefinitionValue(String columnDefinition, ObjectGenerationContext context, ObjectQuery query) {
            Matcher matcher = REGEX_LENGTH.matcher(columnDefinition);
            if (matcher.find()) {
                return getColumnLengthValue(Integer.valueOf(matcher.group(1)), context, query);
            }
            return getDefaultColumnValue(context, query);
        }
    }

    static class LongColumnGenerator implements ColumnGenerator {
        @Override
        public Object generate(final Class<?> entityClass, final Field field, final ObjectGenerationContext context, final ObjectQuery query) {

            if (isId(field)) {
                return null;
            }
            return getDefaultColumnValue(context, query);
        }

        private Long getDefaultColumnValue(ObjectGenerationContext context, ObjectQuery query) {

            ThreadLocalRandom random = ThreadLocalRandom.current();
            return random.nextLong(Long.MAX_VALUE);
        }


    }

    static class IntegerColumnGenerator implements ColumnGenerator {
        private static final Pattern REGEX_LENGTH = Pattern.compile("tinyint", Pattern.CASE_INSENSITIVE);

        @Override
        public Object generate(final Class<?> entityClass, final Field field, final ObjectGenerationContext context, final ObjectQuery query) {

            if (isId(field)) {
                return null;
            }

            final Column column = field.getDeclaredAnnotation(Column.class);
            if (column != null && column.columnDefinition().length() > 0) {
                return getColumnDefinitionValue(column.columnDefinition(), context, query);
            }
            return getDefaultColumnValue(context, query);
        }

        private Integer getDefaultColumnValue(ObjectGenerationContext context, ObjectQuery query) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            return random.nextInt(Integer.MAX_VALUE);
        }

        private Integer getColumnDefinitionValue(String columnDefinition, ObjectGenerationContext context, ObjectQuery query) {
            if (REGEX_LENGTH.matcher(columnDefinition).find()) {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                return random.nextInt(127);
            }
            return Integer.class.cast(context.generate(query));
        }
    }

}
