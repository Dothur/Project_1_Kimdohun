package com.example.miniProject.service;

import com.example.miniProject.auth.entity.UserEntity;
import com.example.miniProject.auth.service.AuthService;
import com.example.miniProject.dto.ResponseDto;
import com.example.miniProject.dto.negotiation.NegotiationDto;
import com.example.miniProject.dto.negotiation.ResponseNegotiationPageDto;
import com.example.miniProject.entity.NegotiationEntity;
import com.example.miniProject.entity.SalesItemEntity;
import com.example.miniProject.repository.NegotiationRepository;
import com.example.miniProject.repository.SalesItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NegotiationService {
    private final SalesItemRepository salesItemRepository;
    private final NegotiationRepository negotiationRepository;
    private final AuthService authService;

    public ResponseDto createNegotiation(Long itemId, NegotiationDto dto) {
        UserEntity userEntity = authService.getUser();
        Optional<SalesItemEntity> salesItemEntity = salesItemRepository.findById(itemId);
        if (salesItemEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        NegotiationEntity negotiationEntity = new NegotiationEntity();
        negotiationEntity.setUser(userEntity);
        negotiationEntity.setSuggestedPrice(dto.getSuggestedPrice());
        negotiationEntity.setItem(salesItemEntity.get());
        negotiationEntity.setStatus("제안");

        negotiationRepository.save(negotiationEntity);
        return new ResponseDto("구매 제안이 등록되었습니다.");
    }

    public Page<ResponseNegotiationPageDto> searchAllNegotiation(
            Long itemId, Integer page, Integer limit
    ) {
        UserEntity userEntity = authService.getUser();
        Optional<SalesItemEntity> salesItemEntity = salesItemRepository.findById(itemId);
        if (salesItemEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        SalesItemEntity salesItem = salesItemEntity.get();
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id"));
        Page<NegotiationEntity> negotiationPage;

        // 물품의 주인이 조회
        if (salesItem.getUser().getId().equals(userEntity.getId())) {
            negotiationPage = negotiationRepository.findAllByItemId(itemId, pageable);
        } else {
            negotiationPage = negotiationRepository.findAllByItem_IdAndUser_Id(
                    itemId, userEntity.getId(), pageable);
        }
        if (negotiationPage.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return negotiationPage.map(ResponseNegotiationPageDto::fromEntity);
    }

    public ResponseDto updateNegotiation(Long itemId, Long proposalId, NegotiationDto dto) {
        UserEntity userEntity = authService.getUser();
        if (dto.getStatus() != null)
            return updateNegotiationChangeStatus(itemId, proposalId, dto);
        Optional<SalesItemEntity> salesItemEntity = salesItemRepository.findById(itemId);
        if (salesItemEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<NegotiationEntity> negotiationEntity = negotiationRepository.findById(proposalId);
        if (negotiationEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        NegotiationEntity negotiationTargetEntity = negotiationEntity.get();
        if (!negotiationTargetEntity.getUser().getId().equals(userEntity.getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        negotiationTargetEntity.setSuggestedPrice(dto.getSuggestedPrice());
        negotiationRepository.save(negotiationTargetEntity);

        return new ResponseDto("제안이 수정되었습니다.");
    }

    public ResponseDto updateNegotiationChangeStatus(Long itemId, Long proposalId, NegotiationDto dto) {
        UserEntity userEntity = authService.getUser();
        Optional<SalesItemEntity> salesItemEntity = salesItemRepository.findById(itemId);
        if (salesItemEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Optional<NegotiationEntity> negotiationEntity = negotiationRepository.findById(proposalId);
        if (negotiationEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        SalesItemEntity salesItem = salesItemEntity.get();
        NegotiationEntity negotiation = negotiationEntity.get();

        // 판매자의 경우
        if (salesItem.getUser().getId().equals(userEntity.getId())) {
            if (dto.getStatus().equals("수락") || dto.getStatus().equals("거절")) {
                negotiationEntity.get().setStatus(dto.getStatus());
                negotiationRepository.save(negotiationEntity.get());
                return new ResponseDto("제안의 상태가 변경되었습니다.");
            }
        }
        // 구매자의 경우
        else {
            if (!negotiation.getUser().getId().equals(userEntity.getId()))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

            if (negotiationEntity.get().getStatus().equals("수락")){
                if (dto.getStatus().equals("확정")) {
                    salesItem.setStatus("판매 완료");
                    salesItemRepository.save(salesItem);

                    List<NegotiationEntity> otherNegotiations = negotiationRepository.findAllByItemIdAndIdNot(itemId, proposalId);
                    for (NegotiationEntity entity : otherNegotiations) {
                        entity.setStatus("거절");
                    }
                    negotiationRepository.saveAll(otherNegotiations);

                    negotiationEntity.get().setStatus(dto.getStatus());
                    negotiationRepository.save(negotiationEntity.get());

                    return new ResponseDto("구매가 확정되었습니다.");
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }


    public ResponseDto deleteNegotiation(Long itemId, Long proposalId) {
        UserEntity userEntity = authService.getUser();
        Optional<SalesItemEntity> salesItemEntity = salesItemRepository.findById(itemId);
        if (salesItemEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Optional<NegotiationEntity> negotiationEntity = negotiationRepository.findById(proposalId);
        if (negotiationEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        NegotiationEntity negotiationTargetEntity = negotiationEntity.get();
        if (!negotiationTargetEntity.getUser().getId().equals(userEntity.getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        negotiationRepository.delete(negotiationTargetEntity);
        return new ResponseDto("제안을 삭제했습니다.");
    }
}
