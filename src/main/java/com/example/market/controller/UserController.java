package com.example.market.controller;

import com.example.market.dto.JoinDto;
import com.example.market.entity.CustomUserDetails;
import com.example.market.service.JpaUserDetailsManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final JpaUserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;
//    @GetMapping("/register")
//    public void register(){
//
//    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> joinRequest(
            @Valid @RequestBody JoinDto joinDto){
        if (joinDto.getPassword().equals(joinDto.getPasswordCheck())) {
            log.info("password match!");
            UserDetails details = CustomUserDetails.builder()
                    .userId(joinDto.getUserId())
                    .password(passwordEncoder.encode(joinDto.getPassword()))
                    .phone(joinDto.getPhone())
                    .email(joinDto.getEmail())
                    .address(joinDto.getAddress())
                    .build();
            log.info("detals.getUsername()="+details.getUsername());
            manager.createUser(details);
            Map<String,String> responseBody = new HashMap<>();
            responseBody.put("message", "가입 완료");
            return ResponseEntity.ok(responseBody);
        }
        log.warn("password does not match..");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    // 에러처리는 manager에서 진행하지만 postman에서 에러 메시지 출력을 위해
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(responseBody);
    }
}
