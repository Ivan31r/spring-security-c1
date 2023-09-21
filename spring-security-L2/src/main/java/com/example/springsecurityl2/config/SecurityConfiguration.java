package com.example.springsecurityl2.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console*"));
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        return security.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/h2-console*")).permitAll()).build();
//    }

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspection) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspection);

        http.csrf(csrfConfigurer ->
                csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/h2-console"),
                        PathRequest.toH2Console()));

        http.headers(headersConfigurer ->
                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
//
//        http.authorizeHttpRequests(auth ->
//                auth
//                        .requestMatchers(mvcMatcherBuilder.pattern("/h2-console")).permitAll()
//                        //This line is optional in .authenticated() case as .anyRequest().authenticated()
//                        //would be applied for H2 path anyway
//                        .requestMatchers(PathRequest.toH2Console()).authenticated()
//                        .anyRequest().authenticated()
//        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        return http.build();

    }
}
