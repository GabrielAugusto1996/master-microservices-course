package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardDto> fetchCardDetails(String mobileNumber) {
        return ResponseEntity.noContent().build();
    }
}
