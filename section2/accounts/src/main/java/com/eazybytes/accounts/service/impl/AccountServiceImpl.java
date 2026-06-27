package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        if (customerRepository.findByMobileNumber(customerDto.mobileNumber()).isPresent()) {
            throw new CustomerAlreadyExistsException(
                    String.format("The customer with the mobile number: %s already exists", customerDto.mobileNumber())
            );
        }

        Customer customer = customerRepository.save(
                customerDto.toNewEntity()
        );

        accountRepository.save(Account.newAccount(customer));
    }
}
