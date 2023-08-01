package com.example.miniProject.entity;

import com.example.miniProject.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "negotiation")
public class NegotiationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private SalesItemEntity item;
    @Column(nullable = false)
    private Integer suggestedPrice;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String writer;
    private String password;
}
