package com.example.miniProject.dto.item;

import com.example.miniProject.entity.SalesItemEntity;
import lombok.Data;

@Data
public class ResponseItemPageDto {
    private Long id;
    private String title;
    private String description;
    private Integer minPriceWanted;
    private String imageUrl;
    private String status;

    public static ResponseItemPageDto fromEntity(SalesItemEntity entity){
        ResponseItemPageDto dto = new ResponseItemPageDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setImageUrl(entity.getImageUrl());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
