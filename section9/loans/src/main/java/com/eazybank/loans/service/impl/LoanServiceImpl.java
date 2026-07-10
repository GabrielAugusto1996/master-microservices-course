package com.eazybank.loans.service.impl;

import com.eazybank.loans.dto.LoanDto;
import com.eazybank.loans.entity.Loan;
import com.eazybank.loans.exception.LoanAlreadyExistsException;
import com.eazybank.loans.exception.ResourceNotFoundException;
import com.eazybank.loans.repository.LoanRepository;
import com.eazybank.loans.service.LoanService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public void createLoan(LoanDto loanDto) {
        if (loanRepository.findByMobileNumber(loanDto.mobileNumber()).isPresent()) {
            throw new LoanAlreadyExistsException(
                    String.format("The loan with the mobile number: %s already exists", loanDto.mobileNumber())
            );
        }

        loanRepository.save(loanDto.toEntity());
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
       return loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)).toDto();
    }

    @Override
    @Transactional
    public void updateLoanByMobileNumber(LoanDto loanDto, String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        loanRepository.save(loan);
    }

    @Override
    @Transactional
    public void deleteLoanByMobileNumber(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

        loanRepository.deleteById(loan.getLoanId());
    }
}
