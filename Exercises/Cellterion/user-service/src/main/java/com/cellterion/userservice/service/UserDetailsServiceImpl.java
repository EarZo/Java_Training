package com.cellterion.userservice.service;

import com.cellterion.userservice.model.CustomUserDetailsImpl;
import com.cellterion.userservice.model.WebsiteUser;
import com.cellterion.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WebsiteUser websiteUser = userRepository.findByUsername(username);

        if (websiteUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return CustomUserDetailsImpl.build(websiteUser);
    }
}
