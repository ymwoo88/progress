package com.woo.progress.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.woo.progress.repository.entities.Menu;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 1427195558L;

    public static final QMenu menu = new QMenu("menu");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> depth = createNumber("depth", Integer.class);

    public final NumberPath<Long> key = createNumber("key", Long.class);

    public final StringPath name = createString("name");

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

