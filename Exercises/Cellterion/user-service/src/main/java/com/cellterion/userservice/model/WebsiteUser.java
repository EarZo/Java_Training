package com.cellterion.userservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data @NoArgsConstructor class WebsiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean admin;
    private boolean enabled;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;

    public WebsiteUser(WebsiteUser websiteUser) {
        this.userId = websiteUser.getUserId();
        this.username = websiteUser.getUsername();
        this.password = websiteUser.getPassword();
        this.firstName = websiteUser.getFirstName();
        this.lastName = websiteUser.getLastName();
        this.enabled = websiteUser.isEnabled();
        this.accountNonLocked = websiteUser.isAccountNonLocked();
        this.accountNonExpired = websiteUser.isAccountNonExpired();
        this.credentialsNonExpired = websiteUser.isCredentialsNonExpired();
        this.roles = websiteUser.getRoles();
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "websiteUser_role",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")}
            )
    private List<Role> roles;
}
