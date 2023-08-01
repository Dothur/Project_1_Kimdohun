package com.example.miniProject.dto.comment;

import com.example.miniProject.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long itemId;
    private String writer;
    private String password;
    private String content;

    public static CommentDto fromEntity(CommentEntity entity){
        CommentDto dto = new CommentDto();
        dto.setItemId(entity.getItem().getId());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setContent(entity.getContent());
        return dto;
    }
}
