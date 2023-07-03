package com.example.market.controller;

import com.example.market.dto.ImageDto;
import com.example.market.dto.ItemDto;
import com.example.market.dto.PasswordDto;
import com.example.market.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    // 아이템 등록 - 작성된 dto의 형태로 새로운 Article을 db에 저장
    @PostMapping
    public ResponseEntity<Map<String,String>> create(@RequestBody ItemDto dto) {
        service.createItem(dto);

        // 응답 메시지
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message","등록이 완료되었습니다.");
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
    @GetMapping("/{itemId}")
    public ItemDto read(@PathVariable("itemId")Long itemId){
        return service.readItem(itemId);
    }

    // GET /items/page
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
    @PutMapping("/{itemId}")
    public ResponseEntity<Map<String,String>> update(@PathVariable("itemId")Long itemId,
                          @RequestBody ItemDto dto) {
        service.updateItem(itemId,dto);

        // 응답 메시지
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message","물품이 수정되었습니다.");
        return ResponseEntity.ok(responseBody);
    }
    //

    // DELETE /items/{id}
    // 해당하는 id를 가진 item를 삭제하는 메소드이다.
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable("itemId")Long itemId,
                       @RequestBody PasswordDto passwordDto) {
        service.deleteItem(itemId,passwordDto);

        // 응답 메시지
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message","물품이 삭제되었습니다.");
        return ResponseEntity.ok(responseBody);
    }
    // 진행중
    // PUT /items/{id}/image
    @PutMapping(value = "/{itemId}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String,String>>  uploadImage(@PathVariable("itemId")Long itemId,
                                                           @RequestBody ImageDto imageDto

    )throws IOException {
//        // 저장할 경로를 생성한다.
//        Files.createDirectories(Path.of("image"));
//
//        // 저장할 파일 이름을 포함한 경로 작성
//        Path uploadTo = Path.of("image/image.png");
//
//        // 저장한다.
//        MultipartFile multipartFile = imageDto.getMultipartFile();
//        multipartFile.transferTo(uploadTo);

        service.updateItemImage(itemId,imageDto);
        // 응답 메시지
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("message", "이미지가 등록되었습니다.");
        return ResponseEntity.ok(responseBody);
    }

}
