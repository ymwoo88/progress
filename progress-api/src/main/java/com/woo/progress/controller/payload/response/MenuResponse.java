package com.woo.progress.controller.payload.response;

import com.woo.progress.repository.entities.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MenuResponse {
    private Long key;
    private String code;
    private String name;
    private Integer depth;

    public static MenuResponse of(Menu menu) {
        return MenuResponse.builder()
                .key(menu.getKey())
                .code(menu.getCode())
                .name(menu.getName())
                .depth(menu.getDepth())
                .build();
    }
}
