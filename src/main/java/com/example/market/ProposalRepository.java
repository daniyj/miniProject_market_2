package com.example.market;

import com.example.market.entity.ProposalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {
    Page<ProposalEntity> findAllByItemId(Long itemId, Pageable pageable);

    Optional<ProposalEntity> findByWriter(String writer);

}
