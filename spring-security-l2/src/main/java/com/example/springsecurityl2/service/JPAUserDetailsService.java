package com.example.springsecurityl2.service;

import com.example.springsecurityl2.entities.User;
import com.example.springsecurityl2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class JPAUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        return new SecurityUser(userByUsername.orElseThrow(() -> new UsernameNotFoundException("Error!")));
    }
}
