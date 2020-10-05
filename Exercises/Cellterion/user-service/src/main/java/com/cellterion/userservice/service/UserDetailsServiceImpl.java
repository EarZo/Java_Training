package com.cellterion.userservice.service;

import com.cellterion.userservice.model.CustomUserDetailsImpl;
import com.cellterion.userservice.model.WebsiteUser;
import com.cellterion.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<WebsiteUser> optionalWebsiteUser = userRepository.findByUsername(username);
        optionalWebsiteUser.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password!"));

        UserDetails userDetails = new CustomUserDetailsImpl(optionalWebsiteUser.get());

        // Throw exception if account disabled, locked, expired or credentials expired
        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;

        /*if (websiteUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return CustomUserDetailsImpl.build(websiteUser);*/
    }
}
