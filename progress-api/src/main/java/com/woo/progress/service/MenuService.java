package com.woo.progress.service;


import com.woo.progress.controller.payload.response.MenuResponse;
import com.woo.progress.repository.entities.Menu;

import java.util.List;

public interface MenuService {
    List<MenuResponse> getList(Menu menu);
    MenuResponse save(Menu menu);
    MenuResponse update(Menu menu);
    void delete(Menu menu);
}
