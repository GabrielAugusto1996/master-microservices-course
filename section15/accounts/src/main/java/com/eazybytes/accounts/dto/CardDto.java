package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(
        name = "Card Detail",
        description = "Schema to hold Card Details information"
)
public record CardDto(
        @Schema(
                description = "Mobile number of the card",
                example = "6694933940394"
        ) @NotBlank String mobileNumber,
        @Schema(
                description = "Card number of the card",
                example = "33445566778899"
        ) @NotBlank String cardNumber,
        @Schema(
                description = "Type of the card",
                example = "DEBIT | CREDIT"
        ) @NotBlank String type,
        @Schema(
                description = "Total Limit of the card",
                example = "1500000"
        ) @NotNull BigDecimal totalLimit,
        @Schema(
                description = "Amount Used of the card",
                example = "1500000"
        ) @NotNull BigDecimal amountUsed
) {}