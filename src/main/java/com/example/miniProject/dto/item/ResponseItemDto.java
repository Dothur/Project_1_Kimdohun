package com.example.miniProject.dto.item;

import com.example.miniProject.entity.SalesItemEntity;
import lombok.Data;

@Data
public class ResponseItemDto {
    private String username;
    private String title;
    private String description;
    private Integer minPriceWanted;
    private String status;

    public static ResponseItemDto fromEntity(SalesItemEntity entity){
        ResponseItemDto dto = new ResponseItemDto();
        dto.setUsername(entity.getUser().getUsername());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
