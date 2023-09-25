package com.example.springsecurityl6.security.provider;

import com.example.springsecurityl6.entitie.Otp;
import com.example.springsecurityl6.repository.OtpRepository;
import com.example.springsecurityl6.security.authentications.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Component
public class OtpAuthProvider implements AuthenticationProvider {
    @Autowired
    private OtpRepository otpRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = String.valueOf(authentication.getCredentials());
        Optional<Otp> otpByUsername = otpRepository.findOtpByUsername(username);
        if (otpByUsername.isPresent()) {
            return new OtpAuthentication(username, otp, List.of(() -> "read"));
        }
        throw new BadCredentialsException(" oh NOOOOO =[");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}