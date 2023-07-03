package com.example.market.service;

import com.example.market.ItemRepository;
import com.example.market.dto.ImageDto;
import com.example.market.dto.ItemDto;
import com.example.market.dto.PasswordDto;
import com.example.market.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;

    public ItemDto createItem(ItemDto dto) {
        ItemEntity newItem = new ItemEntity();
        newItem.setTitle(dto.getTitle());
        newItem.setDescription(dto.getDescription());
        newItem.setMinPriceWanted(dto.getMinPriceWanted());
        newItem.setWriter(dto.getWriter());
        newItem.setPassword(dto.getPassword());
        newItem.setStatus("판매중");

        return ItemDto.fromEntity(repository.save(newItem));
    }

    public ItemDto readItem(Long id) {
        Optional<ItemEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return ItemDto.fromEntity(entity.get());
        }
        // 404 에러
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    public List<ItemDto> readItemAll(){
        List<ItemDto> itemList = new ArrayList<>();
        // repository에서 엔티티를 찾아
        for (ItemEntity entity : repository.findAll()) {
            // entity->dto로 변환하여 dto리스트에 저장
            itemList.add(ItemDto.fromEntity(entity));
        }
        return itemList; //dto리스트 반환
    }
    public Page<ItemDto> readItemPaged(Integer pageNumber,Integer pageSize){
        // 조회하고 싶은 데이터의 정보를 담는 객체
        // 5개씩 데이터를 나눌 때 0번페이지를 달라고 요청하는 Pageable
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize, Sort.by("id").descending());

        Page<ItemEntity> itemEntityPage = repository.findAll(pageable);
        // map: 전달받은 함수를 각 원소에 인자로 전달된 결과를
        // 다시 모아서 stream 객체로
        Page<ItemDto> itemDtoPage = itemEntityPage.map(ItemDto::fromEntity);

        return itemDtoPage;
    }


    public ItemDto updateItem(Long id, ItemDto dto){
        ItemEntity entity = repository.findById(id).orElseThrow(()-> new
        ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!dto.getPassword().equals(entity.getPassword())) {
            System.out.println("비밀번호 불일치");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // 비밀번호가 일치하는 경우
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setMinPriceWanted(dto.getMinPriceWanted());
        entity.setWriter(dto.getWriter());
        entity.setPassword(dto.getPassword());
        entity.setStatus("판매중");

        return ItemDto.fromEntity(repository.save(entity));
    }

    public void deleteItem(Long id, PasswordDto passwordDto) {
        ItemEntity entity = repository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        // writer에 대한 검사는 넣지 않았음 물품 하나당 작성자는 1번뿐이므로 비밀번호만 검사함.
        if (!passwordDto.getPassword().equals(entity.getPassword())) {
            System.out.println("비밀번호 불일치");
            System.out.println("passwordDto패스워드 = " + passwordDto.getPassword());
            System.out.println("entity.getPassword() = " + entity.getPassword());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        // 비밀번호가 일치하는 경우
        repository.deleteById(id);
    }

    // 이미지 업로드 - 진행중(미완성)
    public void updateItemImage(Long id, ImageDto dto) {
        // 1. 유저 존재 확인
        Optional<ItemEntity> optionalItem = repository.findById(id);
        if(optionalItem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        // 2-1. 폴더 만들기
        String profileDir = String.format("image/%d/",id);
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 2-2 확장자를 포함한 이미지 이름 만들기(profile.확장자)
        String originalFilename = dto.getMultipartFile().getOriginalFilename();
        // 점을 기준으로 이름과 확장자 분리 이름.확장자 => {"이름","확장자"}
        String[] fileNameSplit = originalFilename.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1];
        String profileFilename = "profile."+extension;

        // 2-3. 폴더와 파일 경로를 포함한 이름만들기
        String profilePath = profileDir + profileFilename;

        // 3. MultipartFile을 저장
        try {
            dto.getMultipartFile().transferTo(Path.of(profilePath));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 4. ItemEntity 업데이트  (정적 프로필 이미지를 회수할 수 있는 URL)
        // http://localhost:8080/static/1/profile.png
        ItemEntity entity = optionalItem.get();
        entity.setImageUrl(String.format("/static/%d/%s",id,profileFilename));
//        return ItemDto.fromEntity(repository.save(entity));
    }
}
