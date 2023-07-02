package com.example.miniProject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sales_item")
public class SalesItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private Integer minPriceWanted;

    private String status;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String password;
}
