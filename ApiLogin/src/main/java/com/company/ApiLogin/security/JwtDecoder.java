package com.company.ApiLogin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtDecoder {
    private final JwtProperties properties;

    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC384(properties.getSecretKey()))
                .build()
                .verify(token);
    }
}
