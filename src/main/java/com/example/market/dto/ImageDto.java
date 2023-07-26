package com.example.market.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class ImageDto {

    private MultipartFile image;
    private String writer;
    @NotBlank
    private String password;
}
