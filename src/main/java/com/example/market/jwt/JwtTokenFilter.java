package com.example.market.jwt;

import com.example.market.entity.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    public JwtTokenFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader
                = request.getHeader(HttpHeaders.AUTHORIZATION);
        // authHeader는 null이 아니면서 "Bearer "로 구성되어 있어야
        // 정상적인 인증 정보다.(띄어쓰기 주의!)
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.split(" ")[1];
            if (jwtTokenUtils.validate(token)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                // Claims에 저장된 사용자 이름 회수
                String username = jwtTokenUtils
                        .parseClaims(token)
                        .getSubject();
                // 사용자 인증 정보 생성
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        CustomUserDetails.builder()
                                .userId(username)
                                .build(),
                        token, new ArrayList<>());
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);
                log.info("set security context with jwt");
            } else {
                logger.warn("jwt validation failed");
            }
        }
        filterChain.doFilter(request,response);
    }
}
