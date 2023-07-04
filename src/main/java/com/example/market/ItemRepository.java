package com.example.market;

import com.example.market.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity,Long> {
    // 아이디가 큰 순서대로 최상위 10개
    List<ItemEntity> findTop5ByOrderByIdDesc();
    String findWriterByItemId(Long itemId);

    String findPasswordByItemId(Long itemId);
}
