package com.example.market.controller;

import com.example.market.dto.*;
import com.example.market.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/items/{itemId}/proposals")
@RequiredArgsConstructor
public class ProposalController {
    private final ProposalService service;
    // 제안 등록
    @PostMapping
    public ProposalDto createProposal(@PathVariable("itemId") Long itemId,
                                      @RequestBody ProposalDto dto) {
        return service.createProposal(itemId,dto);
    }
    // 정보 조회를 위해 우선 구현.
    // 비밀번호와 작성자를 넣어야 조회가능하나 모든 정보가 조회 가능해서 추후 수정해야함.
    // 다른 기능을 하며 조회가 필요해서 가장 마지막에 수정하기로함
    @GetMapping
    public Page<ProposalDto> readPage(@PathVariable("itemId") Long itemId,
                                      @RequestParam String writer,
                                      @RequestParam String password)
    {
        return service.readPropAll(itemId,writer,password);
    }
    // 제안 수정
    @PutMapping("/{proposalId}")
    public ProposalDto updateProposal(@PathVariable("itemId") Long itemId,
                                      @PathVariable("proposalId") Long proposalId,
                                      @RequestBody UpdatePropDto updatePropDto) {
        return service.updateProposal(itemId, proposalId, updatePropDto);
    }
    // 제안 삭제
    @DeleteMapping("/{proposalId}")
    public ResponseEntity<Map<String,String>> deleteProposal(@PathVariable("itemId")Long itemId,
                                                             @PathVariable("proposalId")Long proposalId,
                                                             @RequestBody PasswordDto passwordDto) {
        service.deleteProposal(itemId, proposalId, passwordDto);
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message", "제안을 삭제했습니다.");
        return ResponseEntity.ok(responseBody);
    }
    // 제안 상태 변경
    // 우선 status로 하고 성공한뒤 나중에 경로 뺴고 다시 test하기
    @PutMapping("/{proposalId}/status")
    public ProposalDto updateProposalStatus(@PathVariable("itemId") Long itemId,
                                      @PathVariable("proposalId") Long proposalId,
                                      @RequestBody UpdatePropStatusDto updatePropStatusDto) {
        return service.updateProposalStatus(itemId, proposalId, updatePropStatusDto);
    }
}
