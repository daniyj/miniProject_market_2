package com.example.market.dto;

import com.example.market.entity.ProposalEntity;
import lombok.Data;

@Data
public class ProposalDto {
    private Long id;
    private Long itemId;
    private Long suggestedPrice;
    private String writer;
    private String password;
    private String status;


    public static ProposalDto fromEntity(ProposalEntity entity){

        ProposalDto dto = new ProposalDto();
        dto.setId(entity.getId());
        dto.setItemId(entity.getItemId());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());

        return dto;
    }
}
