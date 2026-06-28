package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public record AccountDto(
        @Schema(
                description = "Account number of the account",
                example = "1334445556677"
        )
        Long accountNumber,
        @Schema(
                description = "Account type of the account",
                example = "SAVING | EXPENSE"
        )
        String accountType,
        @Schema(
                description = "Branch address of the account",
                example = "123 Death Star"
        )
        String branchAddress
) {

    public Account toEntity() {
        return Account.builder()
                .accountNumber(this.accountNumber)
                .accountType(this.accountType)
                .branchAddress(this.branchAddress)
                .build();
    }
}
