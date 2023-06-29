package com.example.miniProject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sales_item")
public class SalesItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Integer minPriceWanted;
    private String status;
    private String writer;
    private String password;
}
