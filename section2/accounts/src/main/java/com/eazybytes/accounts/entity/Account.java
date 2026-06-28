package com.eazybytes.accounts.entity;

import com.eazybytes.accounts.constants.AccountContants;
import com.eazybytes.accounts.dto.AccountDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @Column(name = "account_number", updatable = false)
    private Long accountNumber;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    public AccountDto toDto() {
        return new AccountDto(accountNumber, accountType, branchAddress);
    }

    public static Account newAccount(Customer customer) {
        long randowAccNumber = 10000000000L + new Random().nextInt(900000000);

        Account account = Account.builder()
                .accountNumber(randowAccNumber)
                .accountType(AccountContants.SAVINGS)
                .branchAddress(AccountContants.ADDRESS)
                .customerId(customer.getCustomerId())
                .build();

        account.setCreatedBy("account-microservice");
        account.setCreatedAt(LocalDateTime.now());

        return account;
    }
}
