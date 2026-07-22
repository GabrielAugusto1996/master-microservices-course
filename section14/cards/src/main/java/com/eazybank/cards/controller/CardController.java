package com.eazybank.cards.controller;

import com.eazybank.cards.constants.CardContants;
import com.eazybank.cards.controller.api.CardApiController;
import com.eazybank.cards.dto.CardDto;
import com.eazybank.cards.dto.ResponseDto;
import com.eazybank.cards.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CardController implements CardApiController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    @Override
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestBody CardDto cardDto) {

        this.cardService.createCard(cardDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardContants.STATUS_201,
                        CardContants.MESSAGE_201)
                );
    }

    @Override
    public ResponseEntity<CardDto> fetchCardDetails(@NotBlank @RequestParam String mobileNumber) {
        logger.debug("fetchCustomerDetails method start");

        return ResponseEntity.status(HttpStatus.OK)
                .body(cardService.fetchCard(mobileNumber));
    }

    @Override
    public ResponseEntity<CardDto> updateCard(@Valid @RequestBody CardDto cardDto,
                                                           @PathVariable String mobileNumber) {

        this.cardService.updateCardByMobileNumber(cardDto, mobileNumber);

        return ResponseEntity.ok(cardDto);
    }

    @Override
    public ResponseEntity<Void> deleteCard(@PathVariable String mobileNumber) {

        this.cardService.deleteCardByMobileNumber(mobileNumber);

        return ResponseEntity.noContent().build();
    }
}
