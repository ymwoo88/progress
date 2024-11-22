package com.woo.progress.controller;

import com.woo.progress.constance.type.Role;
import com.woo.progress.repository.UserTemp;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private Long key;

    private String name;

    private String id;

    private String password;

    private Role role;

    public UserTemp toEntity() {
        return UserTemp.builder()
                .key(key)
                .id(id)
                .name(name)
                .role(role)
                .build();
    }
}
