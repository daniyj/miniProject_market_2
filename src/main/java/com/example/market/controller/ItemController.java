package com.example.market.controller;

import com.example.market.dto.ItemDto;
import com.example.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // RestController:특정 객체 리턴 시
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    // POST /items
    // 작성된 dto의 형태로 새로운 Article을 db에 저장
    @PostMapping
    public ItemDto create(@RequestBody ItemDto dto) {
        return service.createItem(dto);
    }
    // GET /items
    // 모든 items들을 리스트의 형태로 전부 가져온다.
    @GetMapping
    public List<ItemDto> readAll(){
        return service.readItemAll();
    }
    //  GET /items/{id}
    // 해당하는 id를 가진 Item의 내용을 가져온다.
    @GetMapping("/{id}")
    public ItemDto read(@PathVariable("id")Long id){
        return service.readItem(id);
    }
    // 수정,이미지 첨부, 삭제는 비밀번호 받아서 검사해야함.
    // PUT /items/{id}
    // 해당하는 id를 가진 item을 수정한다.
//    @PutMapping("/items/{id}")
//    public ItemDto update(@PathVariable("id")Long id,
//                          @RequestBody ItemDto dto) {
//        return service.updateItem(id,dto);
//    }
//    // DELETE /items/{id}
//    // 해당하는 id를 가진 item를 삭제하는 메소드이다.
//    @DeleteMapping("items/{id}")
//    public void delete(@PathVariable("id")Long id,
//                       @RequestBody ItemDto dto) {
//        service.deleteItem(id,dto);
//    }


}
