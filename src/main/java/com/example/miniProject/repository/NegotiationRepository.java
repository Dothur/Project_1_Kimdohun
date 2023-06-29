package com.example.miniProject.repository;

import com.example.miniProject.entity.Negotiation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegotiationRepository extends JpaRepository<Negotiation, Long> {
}
