package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountContants;
import com.eazybytes.accounts.controller.api.AccountsApiController;
import com.eazybytes.accounts.dto.AccountContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDetailDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.AccountService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController implements AccountsApiController {

    private final AccountService accountService;

    private final String buildVersion;

    private final AccountContactInfoDto accountContactInfoDto;

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    public AccountsController(AccountService accountService, @Value("${build.version}") String buildVersion, AccountContactInfoDto accountContactInfoDto) {
        this.accountService = accountService;
        this.buildVersion = buildVersion;
        this.accountContactInfoDto = accountContactInfoDto;
    }

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

    @Override
    public ResponseEntity<AccountContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(this.accountContactInfoDto);
    }

    @Override
    @Retry(name = "getBuildInfo", fallbackMethod = "getBuildInfoFallback")
    public ResponseEntity<String> getBuildInfo() {
        logger.debug("getBuildInfo() method Invoked.");
        return ResponseEntity.ok(this.buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {
        logger.debug("getBuildInfoFallback() method Invoked.");
        return ResponseEntity.ok("0.9");
    }
}
