package com.example.market.dto;

import com.example.market.entity.CommentEntity;
import com.example.market.entity.ItemEntity;
import jakarta.annotation.Nullable;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemDto {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Long minPriceWanted;
    @NotBlank
    private String writer;
    @NotBlank
    private String password;

    private String imageUrl;
    private String status;



    public static ItemDto fromEntity(ItemEntity entity){
        ItemDto dto = new ItemDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());

        dto.setImageUrl(entity.getImageUrl());
        dto.setStatus(entity.getStatus());


        return dto;
    }
}
