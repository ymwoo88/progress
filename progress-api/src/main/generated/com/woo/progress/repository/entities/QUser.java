package com.woo.progress.repository.entities;

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

    private static final long serialVersionUID = 1714430129L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final StringPath enName = createString("enName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath menuGroupName = createString("menuGroupName");

    public final StringPath password = createString("password");

    public final EnumPath<com.woo.progress.enums.Role> role = createEnum("role", com.woo.progress.enums.Role.class);

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

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

