package com.example.miniProject.dto.negotiation;

import com.example.miniProject.entity.NegotiationEntity;
import lombok.Data;

@Data
public class ResponseNegotiationPageDto {
    private Long id;
    private String username;
    private Integer suggestedPrice;
    private String status;

    public static ResponseNegotiationPageDto fromEntity(NegotiationEntity entity){
        ResponseNegotiationPageDto dto = new ResponseNegotiationPageDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setSuggestedPrice(entity.getSuggestedPrice());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
