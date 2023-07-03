package com.example.miniProject.service;

import com.example.miniProject.dto.RequestUserDto;
import com.example.miniProject.dto.comment.CommentDto;
import com.example.miniProject.dto.ResponseDto;
import com.example.miniProject.dto.comment.RequestReplyDto;
import com.example.miniProject.dto.comment.ResponseCommentPageDto;
import com.example.miniProject.entity.CommentEntity;
import com.example.miniProject.entity.SalesItemEntity;
import com.example.miniProject.repository.CommentRepository;
import com.example.miniProject.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final SalesItemRepository salesItemRepository;

    public ResponseDto createComment(Long itemId, CommentDto dto) {
        if (!salesItemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setWriter(dto.getWriter());
        commentEntity.setPassword(dto.getPassword());
        commentEntity.setContent(dto.getContent());
        commentEntity.setItemId(itemId);

        commentRepository.save(commentEntity);

        return new ResponseDto("댓글이 등록되었습니다.");
    }

    public Page<ResponseCommentPageDto> searchAllComment(Long itemId, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit,
                Sort.by("id")
        );
        Page<CommentEntity> commentEntityPage = commentRepository.findAllByItemId(itemId, pageable);
        if (commentEntityPage.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return commentEntityPage.map(ResponseCommentPageDto::fromEntity);
    }

    public ResponseDto updateComment(Long itemId, Long commentId, CommentDto dto) {
        if (!salesItemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(commentId);
        if (optionalCommentEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity targetEntity = optionalCommentEntity.get();
        validateWriterAndPassword(targetEntity, dto.getWriter(), dto.getPassword());

        targetEntity.setContent(dto.getContent());
        commentRepository.save(targetEntity);

        return new ResponseDto("댓글이 수정되었습니다.");
    }

    public ResponseDto createReply(Long itemId, Long commentId, RequestReplyDto dto) {
        Optional<SalesItemEntity> salesItemEntity = salesItemRepository.findById(itemId);
        if (salesItemEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Optional<CommentEntity> commentEntity = commentRepository.findById(commentId);
        if (commentEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!salesItemEntity.get().getWriter().equals(dto.getWriter())
            || !salesItemEntity.get().getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        CommentEntity entity = commentEntity.get();
        entity.setReply(dto.getReply());
        commentRepository.save(entity);
        return new ResponseDto("댓글에 답변이 추가되었습니다.");
    }

    public ResponseDto deleteComment(Long itemId, Long commentId, RequestUserDto dto) {
        if (!salesItemRepository.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(commentId);
        if (optionalCommentEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity targetEntity = optionalCommentEntity.get();
        validateWriterAndPassword(targetEntity, dto.getWriter(), dto.getPassword());

        commentRepository.delete(targetEntity);
        return new ResponseDto("댓글을 삭제했습니다.");
    }

    private void validateWriterAndPassword(CommentEntity commentEntity, String writer, String password) {
        if (!commentEntity.getPassword().equals(password) || !commentEntity.getWriter().equals(writer)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
