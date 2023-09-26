package com.example.springsecurityl6.security.filter;

import com.example.springsecurityl6.entitie.Otp;
import com.example.springsecurityl6.repository.OtpRepository;
import com.example.springsecurityl6.security.authentications.OtpAuthentication;
import com.example.springsecurityl6.security.authentications.UsernamePasswordAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class UserNamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OtpRepository otpRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //step 1: username & password
        //step2: username & otp

        var username = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");
        if (otp == null) {
            //step 1
            Authentication usernamePasswordAuthentication = new UsernamePasswordAuthentication(username, password);
            authenticationManager.authenticate(usernamePasswordAuthentication);
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
//            we generate an OTP
//            String code = String.valueOf(new Random().nextInt(9999) + 1000);
            String code = String.valueOf(999);
            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(code);
            otpRepository.save(otpEntity);
        } else {
            //step 2
            Authentication otpAuthentication = new OtpAuthentication(username, otp);
            authenticationManager.authenticate(otpAuthentication);
//            SecurityContextHolder.getContext().setAuthentication(otpAuthentication);
//            we issue a token
            response.setHeader("Authorization", String.valueOf(UUID.randomUUID()));
        }

    }
}
