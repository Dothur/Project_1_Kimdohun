package com.example.miniProject.controller;

import com.example.miniProject.dto.ResponseDto;
import com.example.miniProject.dto.negotiation.NegotiationDto;
import com.example.miniProject.dto.negotiation.ResponseNegotiationPageDto;
import com.example.miniProject.service.NegotiationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/proposals")
public class NegotiationController {
    private final NegotiationService negotiationService;

    @PostMapping
    public ResponseEntity<ResponseDto> createNegotiation(
            @PathVariable("itemId") Long itemId,
            @RequestBody NegotiationDto dto
    ) {
        ResponseDto response = negotiationService.createNegotiation(itemId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Page<ResponseNegotiationPageDto> searchAllNegotiation(
            @PathVariable("itemId") Long itemId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "25") Integer pageNumber
    ) {
        return negotiationService.searchAllNegotiation(itemId, page, pageNumber);
    }

    @PutMapping("/{proposalId}")
    public ResponseEntity<ResponseDto> updateNegotiation(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long proposalId,
            @RequestBody NegotiationDto dto
    ) {
        ResponseDto response = negotiationService.updateNegotiation(itemId, proposalId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{proposalId}")
    public ResponseEntity<ResponseDto> deleteNegotiation(
            @PathVariable("itemId") Long itemId,
            @PathVariable("proposalId") Long proposalId
    ) {
        ResponseDto response = negotiationService.deleteNegotiation(itemId, proposalId);
        return ResponseEntity.ok(response);
    }
}
