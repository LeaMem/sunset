package com.lea.oauth.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtProvider {

    @Autowired
    ObjectMapper objectMapper;

    private static final String key = "secret-key";

    private static final Long validityInMilliseconds = TimeUnit.HOURS.toMinutes(1);


    public String createToken(Authentication authentication) throws IOException {

        System.out.println(authentication.getClass());

        User user = (User) authentication.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        long now = System.currentTimeMillis();

        long expired = now + validityInMilliseconds;


        // hmac
        Algorithm algorithm = Algorithm.HMAC256(key);

        return JWT.create()
                .withIssuer("lea")
                .withExpiresAt(new Date(expired))
                .withClaim("user", objectMapper.writeValueAsString(authentication))
                .sign(algorithm);

    }

    public Authentication decodeToken(String token) throws Exception {
        // hmac
        Algorithm algorithm = Algorithm.HMAC256(key);
        DecodedJWT decodedJWT = JWT.decode(token);

        String user = decodedJWT.getClaim("user").asString();

        return objectMapper.readValue(user, UsernamePasswordAuthenticationToken.class);
    }


}
