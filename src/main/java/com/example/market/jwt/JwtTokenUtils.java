package com.example.market.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
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
    private final JwtParser jwtParser;
    public JwtTokenUtils(@Value("${jwt.secret}") String jwtSecret){
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.signingKey)
                .build();
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

    public boolean validate(String token) {
        // parseClaimsJws를 사용해 정보를 해석
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn("invalid jwt");
        }
        return false;
    }

    // JWT를 인자로 받고, 그 JWT를 해석해 사용자 정보를 회수하는 메소드
    public Claims parseClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

}
