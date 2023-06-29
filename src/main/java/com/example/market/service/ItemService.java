package com.example.market.service;

import com.example.market.ItemRepository;
import com.example.market.dto.ItemDto;
import com.example.market.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
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
}