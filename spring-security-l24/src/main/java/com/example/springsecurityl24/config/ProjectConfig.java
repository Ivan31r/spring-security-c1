package com.example.springsecurityl24.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        var usd = new InMemoryUserDetailsManager();
        var userJohn = User.withUsername("john")
                .password("12345")
//                .authorities("read")
                .authorities("ROLE_ADMIN")
//                .roles("ADMIN")
                .build();
        var userBull = User.withUsername("bill")
                .password("12345")
                .roles("MANAGER")
                .build();

        usd.createUser(userJohn);
        usd.createUser(userBull);
        return usd;
    }

    /*
        authorities - лучше использовать когда выделяем права на действия ( read\write\execute)
        role  - когда разделяем по ролям (badge) (разделяем по группам)
        authorities ROLE_ADMIN = roles ADMIN
        authorities\role  следуют одному контракту - GrantedAuthority

     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.cors().disable();

        /*
        - access() SpeL - лучше не использовать
        */

//        по сути это Endpoint auth roles config
//        Еще есть Method Security
        http
                .authorizeRequests()
                .mvcMatchers("/hello").hasRole("ADMIN")
                .mvcMatchers("/ciao").hasRole("MANAGER")
                .anyRequest().authenticated();
    }
}
