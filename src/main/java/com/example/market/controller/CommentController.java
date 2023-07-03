package com.example.market.controller;

import com.example.market.dto.CommentDto;
import com.example.market.dto.ItemDto;
import com.example.market.dto.PasswordDto;
import com.example.market.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items/{itemId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    // 댓글 등록
    //POST /items/{itemId}/comments
    @PostMapping
    public ResponseEntity<Map<String, String>>
    create(@PathVariable("itemId") Long itemId, @RequestBody CommentDto dto) {
        service.createComment(itemId,dto);
        System.out.println("dto = " + dto);
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message", "댓글이 등록되었습니다.");

        return ResponseEntity.ok(responseBody);
    }
    // GET /items/{itemId}/comments/readAll
    // 전체조회 - 모든 댓글들을 리스트의 형태로 전부 가져온다.
    @GetMapping("/readAll")
    public List<CommentDto> readAll(@PathVariable("itemId") Long itemId){

        return service.readCommentAll(itemId);
    }

    // GET /items/{itemId}/comments
    // 페이지 단위 조회
    @GetMapping
    public Page<CommentDto> readPage(@PathVariable("itemId") Long itemId){
        return service.readCommentPaged(itemId);
    }

    // DELETE /item/{itemId}/comments/{commentID}
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Map<String,String>> deleteComment(@PathVariable("itemId")Long itemId,
                              @PathVariable("commentId")Long commentId,
                              @RequestBody PasswordDto passwordDto){
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message", "댓글을 삭제했습니다.");

        service.deleteComment(itemId,commentId,passwordDto);
        return ResponseEntity.ok(responseBody);
    }
}
