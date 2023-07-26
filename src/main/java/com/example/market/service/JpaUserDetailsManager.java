package com.example.market.service;

import com.example.market.UserRepository;
import com.example.market.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    @Override
    public void createUser(UserDetails user) {
        if(this.userExists(user.getUsername())){
            log.error("중복 유저 존재");
            throw new ResponseStatusException(HttpStatus.CONFLICT,"이미 존재하는 ID 입니다.");
        }
        try {
            this.userRepository.save(((CustomUserDetails) user).newEntity());
            log.info("save user entity");
        } catch (ClassCastException e) {
            log.error("failed to cast to {}", CustomUserDetails.class);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String userId) {
        log.info("userId="+userId);
        log.info(String.valueOf(userRepository.existsByUserId(userId)));
        return userRepository.existsByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
