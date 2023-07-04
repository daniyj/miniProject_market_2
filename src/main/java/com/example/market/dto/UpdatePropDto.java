package com.example.market.dto;

import lombok.Data;

@Data
public class UpdatePropDto {
    private String writer;
    private String password;
    private Long suggestedPrice;
}
