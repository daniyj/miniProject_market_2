package com.example.market;

import com.example.market.entity.CommentEntity;
import com.example.market.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    // 아이디가 큰 순서대로 최상위 10개
//    List<ItemEntity> findTop5ByOrderByIdDesc();
    List<CommentEntity> findAllByItemId(Long itemId);
    Page<CommentEntity> findAllByItemId(Long itemId, Pageable pageable);
}
