package com.example.springsecurityl6.config;

import com.example.springsecurityl6.security.filter.UserNamePasswordAuthFilter;
import com.example.springsecurityl6.security.provider.OtpAuthProvider;
import com.example.springsecurityl6.security.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
//@EnableWebSecurity
public class ProjectConfig {
    @Autowired
    private UserNamePasswordAuthFilter userNamePasswordAuthFilter;
    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
    @Autowired
    private OtpAuthProvider otpAuthProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(usernamePasswordAuthProvider, otpAuthProvider));
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  //v1
        http.addFilterAt(userNamePasswordAuthFilter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/h2-console")).permitAll().anyRequest().permitAll());
        http.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))).headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }
}
