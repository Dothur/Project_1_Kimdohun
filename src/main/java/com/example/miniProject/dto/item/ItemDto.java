package com.example.miniProject.dto.item;

import com.example.miniProject.entity.SalesItemEntity;
import lombok.Data;

@Data
public class ItemDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Integer minPriceWanted;
    private String status;
    private String writer;
    private String password;

    public static ItemDto fromEntity(SalesItemEntity entity){
        ItemDto dto = new ItemDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImageUrl(entity.getImageUrl());
        dto.setMinPriceWanted(entity.getMinPriceWanted());
        dto.setStatus(entity.getStatus());
        dto.setWriter(entity.getWriter());
        dto.setPassword(entity.getPassword());
        return dto;
    }
}
