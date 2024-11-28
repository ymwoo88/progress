package com.woo.progress.controller;

import com.woo.progress.controller.payload.request.MenuGroupRequest;
import com.woo.progress.controller.payload.response.MenuGroupResponse;
import com.woo.progress.service.MenuGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/menuGroup")
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @GetMapping("/list")
    public List<MenuGroupResponse> getMenuGroups() {
        return menuGroupService.getList();
    }

    @GetMapping
    public MenuGroupResponse getMenuGroup(@RequestBody MenuGroupRequest menuGroupRequest) {
        MenuGroupResponse menuGroupResponse = menuGroupService.get(menuGroupRequest.getName());
        return menuGroupResponse;
    }

    @PostMapping
    public MenuGroupResponse saveMenu(@RequestBody MenuGroupRequest menuGroupRequest) {
        return menuGroupService.save(menuGroupRequest.toEntityList());
    }

    @PutMapping
    public MenuGroupResponse updateMenu(@RequestBody MenuGroupRequest menuGroupRequest) {
        return menuGroupService.update(menuGroupRequest.toEntityList());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMenu(@RequestBody MenuGroupRequest menuGroupRequest) {
        menuGroupService.delete(menuGroupRequest.toEntityList());
        return ResponseEntity.ok("delete success.");
    }
}
