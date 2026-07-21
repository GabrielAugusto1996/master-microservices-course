package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.audit.AuditAwareImpl;
import com.eazybytes.accounts.dto.AccountMessageDto;
import com.eazybytes.accounts.dto.CustomerDetailDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.AccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AuditAwareImpl auditAware;
    private final StreamBridge streamBridge;

    @Override
    public void createAccount(CustomerDto customerDto) {
        if (customerRepository.findByMobileNumber(customerDto.mobileNumber()).isPresent()) {
            throw new CustomerAlreadyExistsException(
                    String.format("The customer with the mobile number: %s already exists", customerDto.mobileNumber())
            );
        }

        Customer customer = customerRepository.save(
                customerDto.toEntity()
        );

        Account account = accountRepository.save(Account.newAccount(customer));

        sendCommunication(account, customer);
    }

    private void sendCommunication(Account account, Customer customer) {
        var accountMessageDto = new AccountMessageDto(
          account.getAccountNumber(),
          customer.getName(),
          customer.getEmail(),
          customer.getMobileNumber()
        );

        log.info("Sending Communication request for the details: {}", accountMessageDto);
        var result = streamBridge.send("sendCommunication-out-0", accountMessageDto);
        log.info("Is the communication request successfully processed? {}", result);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));


        return Customer.parseToDto(customer, account);
    }

    @Override
    @Transactional
    public void updateAccountByAccountNumber(CustomerDetailDto customerDetailDto, Long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString()));

        accountRepository.updateAccountDetailsByAccountNumber(customerDetailDto.accountType(),
                customerDetailDto.branchAddress(), auditAware.getCurrentAuditor().orElse("NO_USER"), account.getAccountNumber());

        Customer customer = customerRepository.findById(account.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", account.getCustomerId().toString()));

        customer.setEmail(customerDetailDto.email());
        customer.setMobileNumber(customerDetailDto.mobileNumber());
        customer.setName(customerDetailDto.name());

        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteAccountByAccountNumber(Long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber.toString()));

        customerRepository.deleteById(account.getCustomerId());
        accountRepository.deleteById(account.getAccountNumber());
    }
}
