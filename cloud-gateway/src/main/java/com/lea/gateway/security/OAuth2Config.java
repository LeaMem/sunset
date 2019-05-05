package com.lea.gateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("gateway")
                .secret("secret")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "moblieclient");
    }
}
