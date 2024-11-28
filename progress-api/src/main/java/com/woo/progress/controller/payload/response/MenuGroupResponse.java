package com.woo.progress.controller.payload.response;

import com.woo.progress.repository.entities.Menu;
import com.woo.progress.repository.entities.MenuGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MenuGroupResponse {
    private Long key;
    private String name;
    private List<Menu> menuList;

    public static MenuGroupResponse of(List<MenuGroup> menuGroupList) {
        if (CollectionUtils.isEmpty(menuGroupList)) {
            return MenuGroupResponse.builder().build();
        }

        List<Menu> list = menuGroupList.stream().map(MenuGroup::getMenu).toList();
        MenuGroupResponse menuGroupResponse = MenuGroupResponse.builder()
                .key(menuGroupList.get(0).getKey())
                .name(menuGroupList.get(0).getName())
                .menuList(list)
                .build();
        return menuGroupResponse;
    }
}
