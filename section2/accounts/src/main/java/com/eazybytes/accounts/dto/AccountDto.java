package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Account;

public record AccountDto(Long accountNumber, String accountType,
                         String branchAddress) {

    public Account toEntity() {
        return Account.builder()
                .accountNumber(this.accountNumber)
                .accountType(this.accountType)
                .branchAddress(this.branchAddress)
                .build();
    }
}
