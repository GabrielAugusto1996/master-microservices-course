package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDetailsDto;

/**
 * The interface Customer service.
 */
public interface CustomerService {

    /**
     * Fetch customer details customer details dto.
     *
     * @param mobileNumber the mobile number
     * @return the customer details dto
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}