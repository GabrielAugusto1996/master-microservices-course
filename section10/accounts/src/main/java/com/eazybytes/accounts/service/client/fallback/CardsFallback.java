package com.eazybytes.accounts.service.client.fallback;

import com.eazybytes.accounts.dto.CardDto;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {

    @Override
    public ResponseEntity<CardDto> fetchCardDetails(String mobileNumber) {
        return ResponseEntity.noContent().build();
    }
}
