package com.woo.progress.controller.payload.request;

import com.woo.progress.repository.entities.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MenuRequest {
    private String code;
    private String name;
    private Integer depth;

    public Menu toEntity() {
        return Menu.builder()
                .code(code)
                .name(name)
                .depth(depth)
                .build();
    }
}
