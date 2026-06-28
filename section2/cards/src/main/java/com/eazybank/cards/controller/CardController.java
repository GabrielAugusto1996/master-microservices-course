package com.eazybank.cards.controller;

import com.eazybank.cards.constants.CardContants;
import com.eazybank.cards.controller.api.CardApiController;
import com.eazybank.cards.dto.CardDto;
import com.eazybank.cards.dto.ResponseDto;
import com.eazybank.cards.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/cards", produces = {MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class CardController implements CardApiController {

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
