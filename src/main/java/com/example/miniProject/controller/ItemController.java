package com.example.miniProject.controller;

import com.example.miniProject.dto.*;
import com.example.miniProject.dto.item.ItemDto;
import com.example.miniProject.dto.item.ResponseItemDto;
import com.example.miniProject.dto.item.ResponseItemPageDto;
import com.example.miniProject.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;

    // POST /items
    @PostMapping
    public ResponseEntity<ResponseDto> create(
            @RequestBody
            ItemDto dto
    ) {
        log.info("POST /items " + dto.toString());
        ResponseDto response = service.createItem(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseItemDto read(
            @PathVariable("id") Long id
    ) {
        return service.readItem(id);
    }

    // GET /items?page=()&limit=()
    @GetMapping
    public Page<ResponseItemPageDto> searchAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "25") Integer pageNumber
    ) {
        return service.searchAllItem(page, pageNumber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(
            @PathVariable("id") Long id,
            @RequestBody ItemDto dto
    ) {
        log.info("PUT /items/" + id + dto.toString());
        ResponseDto response = service.updateItem(id, dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping(
            value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ResponseDto> updateImage(
            @PathVariable("id") Long id,
            @RequestParam("image") MultipartFile multipartFile
    ) {
        return ResponseEntity.ok(service.updateImage(id, multipartFile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(
            @PathVariable("id") Long id
    ) {
        ResponseDto response = service.deleteItem(id);
        return ResponseEntity.ok(response);
    }
}
