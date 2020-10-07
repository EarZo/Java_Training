package com.cellterion.userservice.service;

import com.cellterion.userservice.model.CustomUserDetailsImpl;
import com.cellterion.userservice.model.WebsiteUser;
import com.cellterion.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<WebsiteUser> optionalWebsiteUser = userRepository.findByUsername(username);
        optionalWebsiteUser.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password!"));

        UserDetails userDetails = new CustomUserDetailsImpl(optionalWebsiteUser.get());

        // Throw exception if account disabled, locked, expired or credentials expired
        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }

    @Transactional
    public void saveWebsiteUser(WebsiteUser websiteUser){
        websiteUser.setPassword(bCryptPasswordEncoder.encode(websiteUser.getPassword()));
        websiteUser.setEnabled(true);
        websiteUser.setAccountNonLocked(true);
        websiteUser.setAccountNonExpired(true);
        websiteUser.setCredentialsNonExpired(true);

        WebsiteUser savedWebsiteUser = userRepository.save(websiteUser);

        String query = "INSERT INTO websiteUser_role (roleId, userId) VALUES (?, ?)";

        if(websiteUser.isAdmin()) {
            entityManager.joinTransaction();
            entityManager.createNativeQuery(query)
                    .setParameter(1, 1)
                    .setParameter(2, savedWebsiteUser.getUserId())
                    .executeUpdate();
        }else{
            entityManager.joinTransaction();
            entityManager.createNativeQuery(query)
                    .setParameter(1, 2)
                    .setParameter(2, savedWebsiteUser.getUserId())
                    .executeUpdate();
        }
    }
}
