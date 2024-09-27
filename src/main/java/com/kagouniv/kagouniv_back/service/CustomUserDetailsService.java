package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.auth.CustomUserDetails;
import com.kagouniv.kagouniv_back.domain.User;
import com.kagouniv.kagouniv_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByLoginId(username).orElseThrow();

        if (user != null) {
            return new CustomUserDetails(user);
        }
        return null;
    }
}