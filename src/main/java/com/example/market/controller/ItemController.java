package com.example.market.controller;

import com.example.market.dto.ItemDto;
import com.example.market.dto.PasswordDto;
import com.example.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

@RestController // RestController:특정 객체 리턴 시
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    // POST /items
    // 작성된 dto의 형태로 새로운 Article을 db에 저장
    @PostMapping
    public ResponseEntity<Map<String,String>> create(@RequestBody ItemDto dto) {

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message","등록이 완료되었습니다.");
        service.createItem(dto);
        return ResponseEntity.ok(responseBody);
    }
    // GET /items
    // 전체조회 - 모든 items들을 리스트의 형태로 전부 가져온다.
    @GetMapping
    public List<ItemDto> readAll(){
        return service.readItemAll();
    }
    //  GET /items/{id}
    // 단일조회 - 해당하는 id를 가진 Item의 내용을 가져온다.
    @GetMapping("/{id}")
    public ItemDto read(@PathVariable("id")Long id){
        return service.readItem(id);
    }

    // 페이지조회 - page?page=페이지인덱스&limit=데이터수
    // http://localhost:8080/items/page?page=0&limit=5
    @GetMapping("/page")
    public Page<ItemDto> readPage(
            @RequestParam(value = "page",defaultValue = "0")Integer page,
            @RequestParam(value="limit",defaultValue = "5")Integer limit){
        return service.readItemPaged(page,limit);
    }

    // 수정,이미지 첨부, 삭제는 비밀번호 받아서 검사해야함.
    // PUT /items/{id}
//     해당하는 id를 가진 item을 수정한다.
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> update(@PathVariable("id")Long id,
                          @RequestBody ItemDto dto) {
        service.updateItem(id,dto);
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message","물품이 수정되었습니다.");
        return ResponseEntity.ok(responseBody);
    }

    // DELETE /items/{id}
    // 해당하는 id를 가진 item를 삭제하는 메소드이다.
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable("id")Long id,
                       @RequestBody PasswordDto passwordDto) {
        service.deleteItem(id,passwordDto);
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message","물품이 삭제되었습니다.");
        return ResponseEntity.ok(responseBody);
    }


}
