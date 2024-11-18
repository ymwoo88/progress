package com.woo.progress.controller;

import com.woo.progress.constance.type.Role;
import com.woo.progress.repository.User;
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

    public User toEntity() {
        return User.builder()
                .key(key)
                .id(id)
                .name(name)
                .role(role)
                .build();
    }
}
