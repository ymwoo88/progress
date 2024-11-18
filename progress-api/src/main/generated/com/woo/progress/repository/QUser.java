package com.woo.progress.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1427447058L;

    public static final QUser user = new QUser("user");

    public final StringPath id = createString("id");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<com.woo.progress.constance.type.Role> role = createEnum("role", com.woo.progress.constance.type.Role.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

