package com.example.market.service;

import com.example.market.CommentRepository;
import com.example.market.ItemRepository;
import com.example.market.dto.CommentDto;
import com.example.market.dto.PasswordDto;
import com.example.market.dto.ReplyDto;
import com.example.market.dto.UpdateComDto;
import com.example.market.entity.CommentEntity;
import com.example.market.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    public CommentDto createComment(Long itemId, CommentDto dto) {
        // itemId를 ID로 가진 ItemEntity가 존재하는지?
        if (!itemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity newComment = new CommentEntity();
        newComment.setItemId(itemId);
        newComment.setWriter(dto.getWriter());
        newComment.setPassword(dto.getPassword());
        newComment.setContent(dto.getContent());
        newComment.setReply("");

        return CommentDto.fromEntity(commentRepository.save(newComment));
    }

    public Page<CommentDto> readCommentPaged(Long itemId){

        Pageable pageable = PageRequest.of(0,5, Sort.by("id").descending());

        Page<CommentEntity> commentEntityPage = commentRepository.findAllByItemId(itemId,pageable);
        Page<CommentDto> commentDtoPage = commentEntityPage.map(CommentDto::fromEntity);

        return commentDtoPage;
    }
    // 전체조회(테스트위해)
    public List<CommentDto> readCommentAll(Long itemId){
        List<CommentDto> commentList = new ArrayList<>();
        for (CommentEntity entity : commentRepository.findAllByItemId(itemId)) {
            commentList.add(CommentDto.fromEntity(entity));
        }
        return commentList;
    }
    public CommentDto updateComment(Long itemId, Long commentId,
                                    UpdateComDto updateComDto) {
        // id에 해당하는 엔티티가 있는지 검사(Optional), 에러처리
        CommentEntity entity = commentRepository.findById(commentId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // 대상 댓글이 대상 게시글의 댓글이 맞는지
        if(!itemId.equals(entity.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 입력정보가 일치하지 않는 경우
        if (!updateComDto.getWriter().equals(entity.getWriter())) { // 작성자가 일치하지 않는 경우
            System.out.println("작성자가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else if (!updateComDto.getPassword().equals(entity.getPassword())) { // 비밀번호가 일치하지 않는 경우
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        entity.setContent(updateComDto.getContent());
        return CommentDto.fromEntity(commentRepository.save(entity));

    }
    public CommentDto updateCommentReply(Long itemId, Long commentId,
                                         ReplyDto replyDto) {
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CommentEntity entity = commentRepository.findById(commentId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!itemId.equals(entity.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 입력정보가 일치하지 않는 경우
        // 물품에 대한 작성자만 댓글의 답글을 달 수 있다. 따라서 물품의 작성자와 비밀번호와 일치한지를 검사한다.
        if (!replyDto.getWriter().equals(itemEntity.getWriter())) {
            System.out.println("작성자가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else if (!replyDto.getPassword().equals(itemEntity.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        entity.setReply(replyDto.getReply());
        return CommentDto.fromEntity(commentRepository.save(entity));

    }
    public void deleteComment(Long itemId,Long commentId, PasswordDto passwordDto){
        // id에 해당하는 엔터티가 있는지 Optional값으로 받아 검사, 에러 처리
        CommentEntity entity = commentRepository.findById(commentId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 요청한 itemId와 해당하는 댓글의 엔터티의 itemId가 같은지 검사
        if(!itemId.equals(entity.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 입력정보가 일치하지 않는 경우
        if (!passwordDto.getWriter().equals(entity.getWriter())) { // 작성자가 일치하지 않는 경우
            System.out.println("작성자가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else if (!passwordDto.getPassword().equals(entity.getPassword())) { // 비밀번호가 일치하지 않는 경우
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


        commentRepository.deleteById(commentId);
    }
}
