package com.example.miniProject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private SalesItem salesItem;
    private String writer;
    private String password;
    private String content;
    private String reply;
}
