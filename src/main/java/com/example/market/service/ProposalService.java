package com.example.market.service;

import com.example.market.ItemRepository;
import com.example.market.ProposalRepository;
import com.example.market.dto.*;
import com.example.market.entity.CommentEntity;
import com.example.market.entity.ItemEntity;
import com.example.market.entity.ProposalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Page<ProposalDto> readPropAll(Long itemId) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());

        Page<ProposalEntity> proposalEntitiyPage = proposalRepository.findAllByItemId(itemId, pageable);
        Page<ProposalDto> proposalDtoPage = proposalEntitiyPage.map(ProposalDto::fromEntity);

        return proposalDtoPage;
    }
    public Page<ProposalDto> readProp(Long itemId, String writer,String password){
        // 물품 주인 또는 작성자만 조회할 수 있음

        //해당 물품 엔티티 조회
        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        System.out.println("itemEntity.getWriter() = " + itemEntity.getWriter());
        System.out.println("writer = " + writer);
        System.out.println(writer.equals(itemEntity.getWriter()));
        // 해당 제안 엔티티 조회
        ProposalEntity proposalEntity = proposalRepository.findByWriter(writer).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Pageable pageable = PageRequest.of(0, 5, Sort.by("id").descending());

        // 물품 주인인 경우
        if (writer.equals(itemEntity.getWriter())) {
            System.out.println("물품 주인입니다.");
            // 맞으면 비밀번호 검사
            if(!password.equals(itemEntity.getPassword())){
                System.out.println("비밀번호가 일치하지 않습니다.");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            // 물품 주인의 경우 해당 아이템의 모든 구매 제안이 조회 가능하다.
            Page<ProposalEntity> proposalEntitiyPage = proposalRepository.findAllByItemId(itemId, pageable);
            Page<ProposalDto> proposalDtoPage = proposalEntitiyPage.map(ProposalDto::fromEntity);
            return proposalDtoPage;
        }

        // 작성자인지 검사
        if(writer.equals(proposalEntity.getWriter())){
            // 맞으면 비밀번호 검사
            if (!password.equals(proposalEntity.getPassword())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"비밀번호가 일치하지 않습니다.");
            }
            // 구매 제안자의 경우 자신이 등록한 구매 제안만 확인이 가능하다.
            Page<ProposalEntity> proposalEntityPage = proposalRepository.findAllByWriter(proposalEntity.getWriter(),pageable);
            Page<ProposalDto> proposalDtoPage = proposalEntityPage.map(ProposalDto::fromEntity);
            return proposalDtoPage;
        }
        // 물품 주인, 작성자도 아닌 경우 / 정보를 잘못 입력한 경우 에러 처리
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 정보를 찾을 수 없습니다.");
    }

    public ProposalDto updateProposal(Long itemId, Long proposalId, UpdatePropDto updatePropDto) {
        // id에 해당하는 엔티티가 있는지 검사(Optional), 에러처리
        ProposalEntity entity = proposalRepository.findById(proposalId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 대상 제안이 대상 게시글의 제안이 맞는지
        if(!itemId.equals(entity.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        // 입력정보가 일치하지 안흔 경우
        if (!updatePropDto.getWriter().equals(entity.getWriter())) {
            System.out.println("작성자가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (!updatePropDto.getPassword().equals(entity.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        entity.setSuggestedPrice(updatePropDto.getSuggestedPrice());
        return ProposalDto.fromEntity(proposalRepository.save(entity));
    }

    public void deleteProposal(Long itemId, Long proposalId, PasswordDto passwordDto) {
        // id에 해당하는 엔티티가 있는지 검사(Optional), 에러처리
        ProposalEntity entity = proposalRepository.findById(proposalId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 대상 제안이 대상 게시글의 제안이 맞는지
        if(!itemId.equals(entity.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        // 입력정보가 일치하지 안흔 경우
        if (!passwordDto.getWriter().equals(entity.getWriter())) {
            System.out.println("작성자가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (!passwordDto.getPassword().equals(entity.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        proposalRepository.deleteById(proposalId);
    }
    // 제안 상태 변경(물품 작성자만 가능)
    public ProposalDto updateProposalStatus(Long itemId, Long proposalId, UpdatePropStatusDto updatePropStatusDto) {

        // id에 해당하는 엔티티가 있는지 검사(Optional), 에러처리
        ProposalEntity proposalEntity = proposalRepository.findById(proposalId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 대상 제안이 대상 게시글의 제안이 맞는지
        if(!itemId.equals(proposalEntity.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 물품 작성자명과 비밀번호가 일치해야함.
        // 입력정보가 일치하지 않는 경우
        if (!updatePropStatusDto.getWriter().equals(itemEntity.getWriter())) {
            System.out.println("작성자가 일치하지 않습니다.");
            System.out.println("itemEntity.getWriter() = " + itemEntity.getWriter());
            System.out.println("updatePropStatusDto.getWriter() = " + updatePropStatusDto.getWriter());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (!updatePropStatusDto.getPassword().equals(itemEntity.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        proposalEntity.setStatus(updatePropStatusDto.getStatus());
        return ProposalDto.fromEntity(proposalRepository.save(proposalEntity));
    }
    // 제안자가 구매 확정(status=수락인 경우)
    public ProposalDto updateProposalConfirmed(Long itemId, Long proposalId, UpdatePropStatusDto updatePropStatusDto) {
        // id에 해당하는 엔티티가 있는지 검사(Optional), 에러처리
        ProposalEntity proposalEntity = proposalRepository.findById(proposalId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 대상 제안이 대상 게시글의 제안이 맞는지
        if(!itemId.equals(proposalEntity.getItemId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 제안자의 작성자명과 비밀번호가 일치해야함.
        // 입력정보가 일치하지 않는 경우

//        1. 이를 위해서 제안을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
//        2. 이때 구매 제안의 상태는 **확정** 상태가 된다.
//        3. 구매 제안이 확정될 경우, 대상 물품의 상태는 **판매 완료**가 된다.
//        4. 구매 제안이 확정될 경우, 확정되지 않은 다른 구매 제안의 상태는 모두 **거절**이 된다.
        if (!updatePropStatusDto.getWriter().equals(proposalEntity.getWriter())) {
            System.out.println("작성자가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (!updatePropStatusDto.getPassword().equals(proposalEntity.getPassword())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (proposalEntity.getStatus().equals("수락")) {
            // 그 아이템에 해당하는 구매 제안들의 상태를 모두 "거절"로 바꾸기
            for (ProposalEntity proposalEntity1 : proposalRepository.findAllByItemId(itemEntity.getId())) {
                proposalEntity1.setStatus("거절");
            }
            // 수락일시만 그 제안 "확정" 으로 변경
            proposalEntity.setStatus(updatePropStatusDto.getStatus());
            // 물품 상태 판매 완료로 변경
            itemEntity.setStatus("판매 완료");
            System.out.println("itemEntity.getId() = " + itemEntity.getId());
            System.out.println("itemEntity.getStatus() = " + itemEntity.getStatus());
        }
        itemRepository.save(itemEntity);
        return ProposalDto.fromEntity(proposalRepository.save(proposalEntity));
    }
}
