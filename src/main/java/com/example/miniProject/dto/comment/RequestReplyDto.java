package com.example.miniProject.dto.comment;

import com.example.miniProject.entity.CommentEntity;
import lombok.Data;

@Data
public class RequestReplyDto {
    private String writer;
    private String password;
    private String reply;

    public static RequestReplyDto fromEntity(CommentEntity entity){
        RequestReplyDto dto = new RequestReplyDto();
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setReply(entity.getReply());
        return dto;
    }
}
