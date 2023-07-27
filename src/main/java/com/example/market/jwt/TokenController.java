package com.example.market.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("token")
public class TokenController {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;

    public TokenController(JwtTokenUtils jwtTokenUtils, UserDetailsManager manager, PasswordEncoder passwordEncoder) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
    }
    // /token/issue: JWT 발급용 Endpoint
    @PostMapping("/issue")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {
        // 사용자 정보 회수
        UserDetails userDetails
                = manager.loadUserByUsername(dto.getUserId());
        // 기록된 비밀번호와 실제 비밀번호가 다를때
        // passwordEncoder.matches(rawPassword, encodedPassword)
        // 평문 비밀번호와 암호화 비밀번호를 비교할 수 있다.
//        log.info(userDetails.toString());
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));
        return response;
    }
    // 에러 메시지를 보여주기 위한 메소드
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(responseBody);
    }
}
