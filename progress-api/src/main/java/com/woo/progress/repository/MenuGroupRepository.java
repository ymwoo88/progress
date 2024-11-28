package com.woo.progress.repository;

import com.woo.progress.repository.entities.MenuGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuGroupRepository extends CrudRepository<MenuGroup, Long> {
    List<MenuGroup> findByName(String name);
}
