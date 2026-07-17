package com.eazybank.loans.controller;

import com.eazybank.loans.constants.LoanContants;
import com.eazybank.loans.controller.api.LoanApiController;
import com.eazybank.loans.dto.LoanDto;
import com.eazybank.loans.dto.ResponseDto;
import com.eazybank.loans.service.LoanService;
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
public class LoanController implements LoanApiController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;

    @Override
    public ResponseEntity<ResponseDto> createLoan(@Valid @RequestBody LoanDto loanDto) {

        this.loanService.createLoan(loanDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoanContants.STATUS_201,
                        LoanContants.MESSAGE_201)
                );
    }

    @Override
    public ResponseEntity<LoanDto> fetchLoanDetails(@NotBlank @RequestParam String mobileNumber) {
        logger.debug("fetchCustomerDetails method start");

        return ResponseEntity.status(HttpStatus.OK)
                .body(loanService.fetchLoan(mobileNumber));
    }

    @Override
    public ResponseEntity<LoanDto> updateLoan(@Valid @RequestBody LoanDto loanDto,
                                              @PathVariable String mobileNumber) {

        this.loanService.updateLoanByMobileNumber(loanDto, mobileNumber);

        return ResponseEntity.ok(loanDto);
    }

    @Override
    public ResponseEntity<Void> deleteLoan(@PathVariable String mobileNumber) {

        this.loanService.deleteLoanByMobileNumber(mobileNumber);

        return ResponseEntity.noContent().build();
    }
}
