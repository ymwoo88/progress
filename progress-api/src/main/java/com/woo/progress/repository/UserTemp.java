package com.woo.progress.repository;

import com.woo.progress.constance.type.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "KB_USER")
public class UserTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_GROUP_KEY")
    private Long key;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ID")
    private String id;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private Role role;
}
