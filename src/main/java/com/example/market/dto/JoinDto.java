package com.example.market.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JoinDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordCheck;
    private String phone;
    private String email;
    private String address;
}
