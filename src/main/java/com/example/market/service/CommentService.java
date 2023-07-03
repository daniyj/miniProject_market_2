package com.example.market.service;

import com.example.market.CommentRepository;
import com.example.market.ItemRepository;
import com.example.market.dto.CommentDto;
import com.example.market.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public CommentDto create(Long itemId, CommentDto dto) {
        CommentEntity newComment = new CommentEntity();
        newComment.setItemId(itemId);
        newComment.setWriter(dto.getWriter());
        newComment.setPassword(dto.getPassword());
        newComment.setContent(dto.getContent());

        return CommentDto.fromEntity(repository.save(newComment));
    }

    public Page<CommentDto> readCommentPaged(Long itemId){

        Pageable pageable = PageRequest.of(0,5, Sort.by("id").descending());

        Page<CommentEntity> commentEntityPage = repository.findAllByItemId(itemId,pageable);
        Page<CommentDto> commentDtoPage = commentEntityPage.map(CommentDto::fromEntity);

        return commentDtoPage;
    }
    // 전체조회(테스트위해)
    public List<CommentDto> readCommentAll(Long itemId){
        List<CommentDto> commentList = new ArrayList<>();
        for (CommentEntity entity : repository.findAllByItemId(itemId)) {
            commentList.add(CommentDto.fromEntity(entity));
        }
        return commentList;
    }
}
