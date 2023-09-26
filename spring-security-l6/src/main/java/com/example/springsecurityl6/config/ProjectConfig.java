package com.example.springsecurityl6.config;

import com.example.springsecurityl6.security.authentications.UsernamePasswordAuthentication;
import com.example.springsecurityl6.security.filter.UserNamePasswordAuthFilter;
import com.example.springsecurityl6.security.provider.OtpAuthProvider6;
import com.example.springsecurityl6.security.provider.UsernamePasswordAuthProvider6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
//@EnableWebSecurity
public class ProjectConfig {

    @Autowired
    @Lazy
    private UsernamePasswordAuthProvider6 usernamePasswordAuthProvider6;
    @Autowired
    private OtpAuthProvider6 otpAuthProvider6;
    @Autowired
    @Lazy
    private UserNamePasswordAuthFilter userNamePasswordAuthFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  //v1
        http.authenticationProvider(usernamePasswordAuthProvider6).authenticationProvider(otpAuthProvider6);
        http.addFilterAt(userNamePasswordAuthFilter, BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/h2-console")).permitAll().anyRequest().permitAll());
        http.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))).headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
//        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspection) throws Exception {
//        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspection);
//
//        http.csrf(csrfConfigurer ->
//                csrfConfigurer.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/h2-console"),
//                        PathRequest.toH2Console()));
//
//        http.headers(headersConfigurer ->
//                headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
//
//        http.authorizeHttpRequests(auth ->
//                auth
//                        .requestMatchers(mvcMatcherBuilder.pattern("/h2-console")).permitAll()
//                        //This line is optional in .authenticated() case as .anyRequest().authenticated()
//                        //would be applied for H2 path anyway
//                        .requestMatchers(PathRequest.toH2Console()).authenticated()
//                        .anyRequest().authenticated()
//        );
//
//        http.formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());
//
//        return http.build();
//
//    }

//    @Bean //temp solution
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails build = User
//                .withUsername("bill")
//                .password("12345")
//                .authorities("read")
//                .build();
//        userDetailsManager.createUser(build);
//        return userDetailsManager;
//    }
}
