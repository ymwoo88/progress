package com.woo.progress.controller.payload.request;

import com.woo.progress.repository.entities.Menu;
import com.woo.progress.repository.entities.MenuGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MenuGroupRequest {
    private String name;
    private List<Menu> menuList;

    public List<MenuGroup> toEntityList() {
        return menuList.stream()
                .map(menu -> MenuGroup.builder()
                        .name(name)
                        .menu(menu)
                        .build()).toList();
    }
}
