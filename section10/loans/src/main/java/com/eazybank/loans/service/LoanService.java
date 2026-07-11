package com.eazybank.loans.service;

import com.eazybank.loans.dto.LoanDto;

/**
 * The interface Loan service.
 */
public interface LoanService {

    /**
     * Create loan.
     *
     * @param loanDto the loan dto
     */
    void createLoan(LoanDto loanDto);

    LoanDto fetchLoan(String mobileNumber);

    void updateLoanByMobileNumber(LoanDto loanDto, String mobileNumber);

    void deleteLoanByMobileNumber(String mobileNumber);
}
