package com.example.miniProject.repository;

import com.example.miniProject.entity.NegotiationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegotiationRepository extends JpaRepository<NegotiationEntity, Long> {
}
