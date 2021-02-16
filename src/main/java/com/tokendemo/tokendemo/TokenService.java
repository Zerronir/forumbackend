package com.tokendemo.tokendemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${token.secret}")
    String tokenSecret;

    @Value("${token.expiration.time")
    int expiration;


    public String newToken(String user) {
        String token = JWT.create()
                .withSubject(user)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC512(tokenSecret.getBytes()));

        return token;
    }

    public String getSubject(String token) {
        String user = JWT.require(Algorithm.HMAC512(tokenSecret.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        return user;
    }
}
