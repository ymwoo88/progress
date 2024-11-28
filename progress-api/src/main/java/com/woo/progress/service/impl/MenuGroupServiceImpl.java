package com.woo.progress.service.impl;

import com.woo.progress.controller.payload.response.MenuGroupResponse;
import com.woo.progress.controller.payload.response.MenuResponse;
import com.woo.progress.repository.MenuGroupRepository;
import com.woo.progress.repository.MenuRepository;
import com.woo.progress.repository.entities.Menu;
import com.woo.progress.repository.entities.MenuGroup;
import com.woo.progress.service.MenuGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;



@Service
@Transactional
@RequiredArgsConstructor
public class MenuGroupServiceImpl implements MenuGroupService {

    private final MenuGroupRepository menuGroupRepository;


    @Override
    public List<MenuGroupResponse> getList() {
        Map<Menu, List<MenuGroup>> collect = StreamSupport
                .stream(menuGroupRepository.findAll().spliterator(), false)
                .collect(Collectors.groupingBy(MenuGroup::getMenu));
        return collect.values().stream().map(MenuGroupResponse::of).toList();
    }

    @Override
    public MenuGroupResponse get(String name) {
        MenuGroupResponse result = MenuGroupResponse.of(menuGroupRepository.findByName(name));
        return result;
    }

    @Override
    public MenuGroupResponse save(List<MenuGroup> menuGroupList) {
        menuGroupRepository.saveAll(menuGroupList);
        return MenuGroupResponse.of(menuGroupList);
    }

    @Override
    public MenuGroupResponse update(List<MenuGroup> menuGroupList) {
        menuGroupRepository.saveAll(menuGroupList);
        return MenuGroupResponse.of(menuGroupList);
    }

    @Override
    public void delete(List<MenuGroup> menuGroupList) {
        menuGroupRepository.deleteAll(menuGroupList);
    }
}
