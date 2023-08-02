package com.example.market.dto;

import com.example.market.entity.CommentEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDto {
    private Long id;
    private Long itemId; // 대상 물품
    @NotBlank
    private String writer; // 작성자
    @NotBlank
    private String content; // 댓글 내용
    @NotBlank
    private String password;

    private String reply;

    public static CommentDto fromEntity(CommentEntity entity){
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setItemId(entity.getItem().getId());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());
        return dto;
    }


}
