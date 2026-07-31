package com.eazybank.cards.service.impl;

import com.eazybank.cards.dto.CardDto;
import com.eazybank.cards.entity.Card;
import com.eazybank.cards.exception.CardAlreadyExistsException;
import com.eazybank.cards.exception.ResourceNotFoundException;
import com.eazybank.cards.repository.CardRepository;
import com.eazybank.cards.service.CardService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public void createCard(CardDto cardDto) {
        if (cardRepository.findByMobileNumber(cardDto.mobileNumber()).isPresent()) {
            throw new CardAlreadyExistsException(
                    String.format("The card with the mobile number: %s already exists", cardDto.mobileNumber())
            );
        }

        cardRepository.save(cardDto.toEntity());
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
       return cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)).toDto();
    }

    @Override
    @Transactional
    public void updateCardByMobileNumber(CardDto cardDto, String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

        cardRepository.save(card);
    }

    @Override
    @Transactional
    public void deleteCardByMobileNumber(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

        cardRepository.deleteById(card.getCardId());
    }
}
