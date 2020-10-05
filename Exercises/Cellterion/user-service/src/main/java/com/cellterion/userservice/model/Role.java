package com.cellterion.userservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "permissionId", referencedColumnName = "permissionId")}
    )
    private List<Permission> permissions;
}
