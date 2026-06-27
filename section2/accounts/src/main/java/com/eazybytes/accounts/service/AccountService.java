package com.eazybytes.accounts.service;

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
}
