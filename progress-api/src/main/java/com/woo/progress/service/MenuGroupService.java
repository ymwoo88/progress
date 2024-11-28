package com.woo.progress.service;


import com.woo.progress.controller.payload.response.MenuGroupResponse;
import com.woo.progress.repository.entities.MenuGroup;

import java.util.List;

public interface MenuGroupService {
    List<MenuGroupResponse> getList();
    MenuGroupResponse get(String name);
    MenuGroupResponse save(List<MenuGroup> menuGroupList);
    MenuGroupResponse update(List<MenuGroup> menuGroupList);
    void delete(List<MenuGroup> menuGroupList);
}
