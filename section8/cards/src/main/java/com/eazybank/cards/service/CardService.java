package com.eazybank.cards.service;

import com.eazybank.cards.dto.CardDto;

/**
 * The interface Card service.
 */
public interface CardService {

    /**
     * Create card.
     *
     * @param cardDto the card dto
     */
    void createCard(CardDto cardDto);

    CardDto fetchCard(String mobileNumber);

    void updateCardByMobileNumber(CardDto cardDto, String mobileNumber);

    void deleteCardByMobileNumber(String mobileNumber);
}
