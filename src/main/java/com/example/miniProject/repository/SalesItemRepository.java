package com.example.miniProject.repository;

import com.example.miniProject.entity.SalesItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesItemRepository extends JpaRepository<SalesItem, Long> {
}
