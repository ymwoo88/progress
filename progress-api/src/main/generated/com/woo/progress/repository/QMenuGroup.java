package com.woo.progress.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMenuGroup is a Querydsl query type for MenuGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenuGroup extends EntityPathBase<MenuGroup> {

    private static final long serialVersionUID = -1070794887L;

    public static final QMenuGroup menuGroup = new QMenuGroup("menuGroup");

    public final StringPath id = createString("id");

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final NumberPath<Long> menuId = createNumber("menuId", Long.class);

    public final StringPath name = createString("name");

    public QMenuGroup(String variable) {
        super(MenuGroup.class, forVariable(variable));
    }

    public QMenuGroup(Path<? extends MenuGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenuGroup(PathMetadata metadata) {
        super(MenuGroup.class, metadata);
    }

}

