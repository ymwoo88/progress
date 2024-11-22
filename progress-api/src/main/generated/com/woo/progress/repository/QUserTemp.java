package com.woo.progress.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserTemp is a Querydsl query type for UserTemp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTemp extends EntityPathBase<UserTemp> {

    private static final long serialVersionUID = 1550056454L;

    public static final QUserTemp userTemp = new QUserTemp("userTemp");

    public final StringPath id = createString("id");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<com.woo.progress.constance.type.Role> role = createEnum("role", com.woo.progress.constance.type.Role.class);

    public QUserTemp(String variable) {
        super(UserTemp.class, forVariable(variable));
    }

    public QUserTemp(Path<? extends UserTemp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserTemp(PathMetadata metadata) {
        super(UserTemp.class, metadata);
    }

}

