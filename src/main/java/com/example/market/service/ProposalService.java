package com.example.market.service;

import com.example.market.ItemRepository;
import com.example.market.ProposalRepository;
import com.example.market.dto.ProposalDto;
import com.example.market.entity.CommentEntity;
import com.example.market.entity.ProposalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalService {
    private final ProposalRepository proposalRepository;
    private final ItemRepository itemRepository;

    public ProposalDto createProposal(Long itemId, ProposalDto dto) {
//         itemId를 ID로 가진 ItemEntity가 존재하는지?
        if (!itemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        ProposalEntity newProp = new ProposalEntity();
        newProp.setItemId(itemId);
        newProp.setSuggestedPrice(dto.getSuggestedPrice());
        newProp.setWriter(dto.getWriter());
        newProp.setPassword(dto.getPassword());
        newProp.setStatus("제안");

        return ProposalDto.fromEntity(proposalRepository.save(newProp));
    }
    public Page<ProposalDto> readPropAll(Long itemId,ProposalDto dto){
        // 물품 주인 또는 작성자만 조회할 수 있음

        // 물품 주인인 경우
        if (dto.getWriter().equals(itemRepository.findWriterByItemId(itemId))) {
            // 맞으면 비밀번호 검사
            if(!dto.getPassword().equals(itemRepository.findPasswordByItemId(itemId))){
                System.out.println("비밀번호가 일치하지 않습니다.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        // 해당 엔티티 조회
        ProposalEntity proposalEntity = proposalRepository.findById(dto.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 작성자인지 검사
        if(dto.getWriter().equals(proposalEntity.getWriter())){
            // 맞으면 비밀번호 검사
            if (!dto.getPassword().equals(proposalEntity.getPassword())) {
                System.out.println("비밀번호가 일치하지 않습니다.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());

        Page<ProposalEntity> proposalEntitiyPage = proposalRepository.findAllByItemId(itemId, pageable);
        Page<ProposalDto> proposalDtoPage = proposalEntitiyPage.map(ProposalDto::fromEntity);
        return proposalDtoPage;
    }
}
