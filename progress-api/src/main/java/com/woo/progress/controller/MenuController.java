package com.woo.progress.controller;

import com.woo.progress.controller.payload.request.MenuRequest;
import com.woo.progress.controller.payload.response.MenuResponse;
import com.woo.progress.repository.entities.User;
import com.woo.progress.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menus")
    public List<MenuResponse> getMenus(@RequestBody MenuRequest menuRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        log.info("user info = {}", user);
        return menuService.getList(menuRequest.toEntity());
    }

    @PostMapping("/menu")
    public MenuResponse saveMenu(@RequestBody MenuRequest menuRequest) {
        return menuService.save(menuRequest.toEntity());
    }

    @PutMapping("/menu")
    public MenuResponse updateMenu(@RequestBody MenuRequest menuRequest) {
        return menuService.update(menuRequest.toEntity());
    }

    @DeleteMapping("/menu")
    public ResponseEntity<String> deleteMenu(@RequestBody MenuRequest menuRequest) {
        menuService.delete(menuRequest.toEntity());
        return ResponseEntity.ok("delete success.");
    }
}
