package com.example.miniProject.dto.comment;

import com.example.miniProject.entity.CommentEntity;
import lombok.Data;

@Data
public class ResponseCommentPageDto {
    private Long id;
    private String username;
    private String content;
    private String reply;

    public static ResponseCommentPageDto fromEntity(CommentEntity entity){
        ResponseCommentPageDto dto = new ResponseCommentPageDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setContent(entity.getContent());
        dto.setReply(entity.getReply());
        return dto;
    }
}
