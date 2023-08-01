package com.example.miniProject.entity;

import com.example.miniProject.auth.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private SalesItemEntity item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String writer;
    private String password;
    @Column(nullable = false)
    private String content;
    private String reply;
}
