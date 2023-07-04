package com.example.market.controller;

import com.example.market.dto.ProposalDto;
import com.example.market.dto.ReadPropDto;
import com.example.market.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items/{itemId}/proposals")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService service;
    @PostMapping
    public ProposalDto createProposal(@PathVariable("itemId") Long itemId,
                                      @RequestBody ProposalDto dto) {
        return service.createProposal(itemId,dto);
    }
    // 정보 조회를 위해 우선 구현.
    // 추후 비밀번호와 작성자 넣어야 조회 가능하도록 수정해야함
    @GetMapping
    public Page<ProposalDto> readPage(@PathVariable("itemId") Long itemId,
                                      @RequestBody ReadPropDto dto)
    {
        return service.readPropAll(itemId,dto);
    }
}
