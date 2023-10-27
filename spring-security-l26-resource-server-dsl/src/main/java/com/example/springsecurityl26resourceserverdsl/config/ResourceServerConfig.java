package com.example.springsecurityl26resourceserverdsl.config;

import com.nimbusds.jose.shaded.json.JSONArray;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.stream.Collectors;

@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                customizer -> customizer.jwt(
                        jwtCustomizer -> jwtCustomizer.decoder(decoder())
                                .jwtAuthenticationConverter(jwtConverter())
                )
        );
        http.authorizeRequests().mvcMatchers("/demo/**").hasAuthority("read");
    }

    @Bean
    public JwtDecoder decoder() {
        String key = "qwertyuiop[]asdfghjkl;'zxcvbnm,./";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), 0, key.getBytes().length, "AES");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            JSONArray authorities = (JSONArray) jwt.getClaims().get("authorities");
            return authorities.stream().map(String::valueOf).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        });
        return converter;
    }
}
