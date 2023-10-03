package com.example.springsecurityl12.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    /*
     * auth_code (pkce)
     * password -> deprecated
     * client_credentials
     * refresh_token
     * implicit -> deprecated*/

    /* client1 + secret1 используются в качестве бейсик аутентификации
     * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret("secret1")
                .scopes("read")
                .authorizedGrantTypes("password", "refresh_token")
                .and()
                .withClient("client2")
                .secret("secret2")
                .scopes("read")
                .authorizedGrantTypes("authorization_code")
                .redirectUris("http://localhost:9090")
                .and()
                .withClient("client3")
                .secret("secret3")
                .scopes("read", "write")
                .authorizedGrantTypes("client_credentials");
    }
//просто способю смены обычных токенов на JWT
//    @Bean
//    public TokenStore tokenStore(){
//        return new JwtTokenStore(converter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter converter(){
//        return new JwtAccessTokenConverter();
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
