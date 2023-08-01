package com.example.miniProject.auth.entity;

import com.example.miniProject.entity.CommentEntity;
import com.example.miniProject.entity.NegotiationEntity;
import com.example.miniProject.entity.SalesItemEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comment;
    @OneToMany(mappedBy = "user")
    private List<NegotiationEntity> negotiation;
    @OneToMany(mappedBy = "user")
    private List<SalesItemEntity> salesItem;
}
