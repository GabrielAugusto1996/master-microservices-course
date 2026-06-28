package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountContants;
import com.eazybytes.accounts.controller.api.AccountsApiController;
import com.eazybytes.accounts.dto.CustomerDetailDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.AccountService;
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
@RequestMapping(path = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
public class AccountsController implements AccountsApiController {

    private final AccountService accountService;

    @Override
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        this.accountService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountContants.STATUS_201,
                        AccountContants.MESSAGE_201)
                );
    }

    @Override
    public ResponseEntity<CustomerDto> fetchAccountDetails(@NotBlank @RequestParam String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.fetchAccount(mobileNumber));
    }

    @Override
    public ResponseEntity<CustomerDetailDto> updateAccount(@Valid @RequestBody CustomerDetailDto customerDetailDto,
                                                           @PathVariable Long accountNumber) {

        this.accountService.updateAccountByAccountNumber(customerDetailDto, accountNumber);

        return ResponseEntity.ok(customerDetailDto);
    }

    @Override
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountNumber) {

        this.accountService.deleteAccountByAccountNumber(accountNumber);

        return ResponseEntity.noContent().build();
    }
}
