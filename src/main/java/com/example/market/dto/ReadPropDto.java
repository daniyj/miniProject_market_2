package com.example.market.dto;

import lombok.Data;

@Data
public class ReadPropDto {
    // 구매 제안은 대상 물품의 주인과 등록한 사용자만 조회할 수 있다.
    // 조회를 위해 작성자와 비밀번호를 입력해야한다.
    private String writer;
    private String password;
}
