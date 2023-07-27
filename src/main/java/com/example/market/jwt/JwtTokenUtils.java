package com.example.market.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.security.Keys;


@Slf4j
@Component
public class JwtTokenUtils {
    private final Key signingKey;
    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret){
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    // JWT 발급 메소드
    // 주어진 사용자 정보를 바탕으로 JWT를 문자열로 생성
    public String generateToken(UserDetails userDetails) {
        Claims jwtClaims = Jwts.claims()
                // 사용자 정보 등록
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600))); //1h

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signingKey)
                .compact();
    }
}
