package com.example.market;

import com.example.market.entity.CommentEntity;
import com.example.market.entity.ProposalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
    Page<ProposalEntity> findAllByItemId(Long itemId, Pageable pageable);

}
