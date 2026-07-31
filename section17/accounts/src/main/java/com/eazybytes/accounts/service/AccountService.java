package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDetailDto;
import com.eazybytes.accounts.dto.CustomerDto;

/**
 * The interface Account service.
 */
public interface AccountService {

    /**
     * Create account.
     *
     * @param customerDto the customer dto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    void updateAccountByAccountNumber(CustomerDetailDto customerDetailDto, Long accountNumber);

    void deleteAccountByAccountNumber(Long accountNumber);
}
