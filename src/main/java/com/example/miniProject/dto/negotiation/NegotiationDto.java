package com.example.miniProject.dto.negotiation;

import com.example.miniProject.entity.NegotiationEntity;
import lombok.Data;

@Data
public class NegotiationDto {
    private String writer;
    private String password;
    private String status;
    private Integer suggestedPrice;

    public static NegotiationDto fromEntity(NegotiationEntity entity){
        NegotiationDto dto = new NegotiationDto();
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        return dto;
    }
}
