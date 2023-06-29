package com.example.miniProject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "negotiation")
public class Negotiation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private SalesItem salesItem;
    private Integer suggestedPrice;
    private String status;
    private String writer;
    private String password;
}
