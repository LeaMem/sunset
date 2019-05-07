package com.lea.oauth.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtProvider {


    private static final String key = "secret-key";

    private static final Long validityInMilliseconds = 3600000L;


    public static String createToken(Authentication authentication) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        User user = (User) authentication.getPrincipal();

        authentication.getAuthorities();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");


        JWTCreator.Builder builder = JWT.create();

        long now = System.currentTimeMillis();

        long expired = now + TimeUnit.HOURS.toMinutes(1);


        String token = builder.withHeader(map)
                .withClaim("auth", objectMapper.writeValueAsString(authentication))
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(expired))
                .sign(Algorithm.HMAC256(key));


        return token;
    }


}
