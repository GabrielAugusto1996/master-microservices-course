package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.CardDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.LoanDto;
import com.eazybytes.accounts.service.AccountService;
import com.eazybytes.accounts.service.CustomerService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final LoansFeignClient loansFeignClient;
    private final CardsFeignClient cardsFeignClient;
    private final AccountService accountService;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        var customerDto = this.accountService.fetchAccount(mobileNumber);
        ResponseEntity<LoanDto> loanDtoResponseEntity = this.loansFeignClient.fetchLoansDetails(mobileNumber);
        ResponseEntity<CardDto> cardDtoResponseEntity = this.cardsFeignClient.fetchCardDetails(mobileNumber);


        return CustomerDetailsDto.builder()
                .name(customerDto.name())
                .email(customerDto.email())
                .accountDto(customerDto.accountDto())
                .loanDto(loanDtoResponseEntity.getBody())
                .cardDto(cardDtoResponseEntity.getBody())
                .build();
    }
}
