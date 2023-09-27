package com.example.springsecurityl7.service;

import com.example.springsecurityl7.repository.UserRepository;
import com.example.springsecurityl7.security.model.SecurityUserDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecurityUserDecorator(userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user =(")));
    }
}
