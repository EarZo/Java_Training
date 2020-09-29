package com.cellterion.userservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private WebsiteUser websiteUser;

    Set<GrantedAuthority> authorities = null;

    public void setWebsiteUser(WebsiteUser websiteUser) {
        this.websiteUser = websiteUser;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    public WebsiteUser getWebsiteUser() {
        return websiteUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return websiteUser.getPassword();
    }

    @Override
    public String getUsername() {
        return websiteUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
