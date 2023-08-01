package com.example.miniProject.repository;

import com.example.miniProject.entity.NegotiationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NegotiationRepository extends JpaRepository<NegotiationEntity, Long> {
    Page<NegotiationEntity> findAllByItemId(Long itemId, Pageable pageable);

    Page<NegotiationEntity> findAllByItem_IdAndUser_Id(Long itemId, Long userId, Pageable pageable);

    List<NegotiationEntity> findAllByItemIdAndIdNot(Long itemId, Long proposalId);
}
