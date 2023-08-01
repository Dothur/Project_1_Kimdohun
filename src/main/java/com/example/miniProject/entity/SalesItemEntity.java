package com.example.miniProject.entity;

import com.example.miniProject.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String writer;
    private String password;

    @OneToMany(mappedBy = "item")
    private List<CommentEntity> comment;

    @OneToMany(mappedBy = "item")
    private List<NegotiationEntity> negotiation;
}
