package com.example.market.jwt;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
