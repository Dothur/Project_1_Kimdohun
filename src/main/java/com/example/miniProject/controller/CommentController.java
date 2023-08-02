package com.example.miniProject.controller;

import com.example.miniProject.dto.comment.CommentDto;
import com.example.miniProject.dto.ResponseDto;
import com.example.miniProject.dto.comment.RequestReplyDto;
import com.example.miniProject.dto.comment.ResponseCommentPageDto;
import com.example.miniProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/comments")
public class CommentController {
    private final CommentService commentService;

    // POST /items/{itemId}/comments
    @PostMapping
    public ResponseEntity<ResponseDto> createComment(
            @PathVariable("itemId") Long itemId,
            @RequestBody CommentDto dto
    ) {
        ResponseDto response = commentService.createComment(itemId, dto);
        return ResponseEntity.ok(response);
    }

    // GET /items/{itemId}/comments?page=()&limit=()
    @GetMapping
    public Page<ResponseCommentPageDto> searchAll(
            @PathVariable("itemId") Long itemId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "25") Integer pageNumber
    ) {
        return commentService.searchAllComment(itemId, page, pageNumber);
    }

    // PUT /items/{itemId}/comments/{commentId}
    @PutMapping("/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto dto
    ) {
        ResponseDto response = commentService.updateComment(itemId, commentId, dto);
        return ResponseEntity.ok(response);
    }

    // PUT /items/{itemId}/comments/{commentId}/reply
    @PutMapping("/{commentId}/reply")
    public ResponseEntity<ResponseDto> createReply(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId,
            @RequestBody RequestReplyDto dto
    ) {
        ResponseDto response = commentService.createReply(itemId, commentId, dto);

        return ResponseEntity.ok(response);
    }

    // DELETE /items/{itemId}/comments/{commentId}
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(
            @PathVariable("itemId") Long itemId,
            @PathVariable("commentId") Long commentId
    ) {
        ResponseDto response = commentService.deleteComment(itemId, commentId);

        return ResponseEntity.ok(response);
    }
}
