package com.example.springsecurityl8.config;

import com.example.springsecurityl8.security.filter.TokenAuthFilter;
import com.example.springsecurityl8.security.filter.UserNamePasswordAuthFilter;
import com.example.springsecurityl8.security.provider.OtpAuthProvider;
import com.example.springsecurityl8.security.provider.TokenAuthProvider;
import com.example.springsecurityl8.security.provider.UsernamePasswordAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {
    private final UserNamePasswordAuthFilter userNamePasswordAuthFilter;
    private final TokenAuthFilter tokenAuthFilter;
    private final UsernamePasswordAuthProvider usernamePasswordAuthProvider;
    private final OtpAuthProvider otpAuthProvider;
    private final TokenAuthProvider tokenAuthProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(List.of(usernamePasswordAuthProvider, otpAuthProvider, tokenAuthProvider));
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  //v1
        http.addFilterAt(userNamePasswordAuthFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(tokenAuthFilter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/h2-console")).permitAll().anyRequest().permitAll());
        http.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))).headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }

    /*
     Изменив стратегию, сесурити контекст из родительского потока копируется в дочерний поток
     Это один из способов передачи контекста
     !!!Но это работает только если поток создает СПРИНГ!Например @Async!!!
     Поправка: С какой-то версии это работает и со своими потоками.
     */
//    @Bean
//    public InitializingBean initializingBean() {
//        return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//    }
    /*
    Еще один из вариантов, если нам нужно создать свой поток и не потерять секурити контекст, то можно использовать обертки в виде
    DelegatingSecurityContextRunnable \ DelegatingSecurityContextCallable
    или
    DelegatingSecurityContextExecutorService как обертку для ExecutorService
     */
}
