package com.woo.progress.service.impl;

import com.woo.progress.controller.payload.response.MenuResponse;
import com.woo.progress.repository.MenuRepository;
import com.woo.progress.repository.entities.Menu;
import com.woo.progress.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuResponse> getList(Menu menu) {
        return StreamSupport
                .stream(menuRepository.findAll().spliterator(), false)
                .map(MenuResponse::of)
                .toList();
    }

    @Override
    public MenuResponse save(Menu menu) {
        return MenuResponse.of(menuRepository.save(menu));
    }

    @Override
    public MenuResponse update(Menu menu) {
        return MenuResponse.of(menuRepository.save(menu));
    }

    @Override
    public void delete(Menu menu) {
        menuRepository.delete(menu);
    }
}
