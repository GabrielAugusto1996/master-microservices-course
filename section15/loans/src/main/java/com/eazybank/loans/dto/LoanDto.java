package com.eazybank.loans.dto;

import com.eazybank.loans.entity.Loan;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(
        name = "Loan Detail",
        description = "Schema to hold Loan Details information"
)
public record LoanDto(
        @Schema(
                description = "Mobile number of the loan",
                example = "6694933940394"
        ) @NotBlank String mobileNumber,
        @Schema(
                description = "Loan number of the loan",
                example = "33445566778899"
        ) @NotBlank String loanNumber,
        @Schema(
                description = "Type of the loan",
                example = "DEBIT | CREDIT"
        ) @NotBlank String type,
        @Schema(
                description = "Total Loan of the loan",
                example = "1500000"
        ) @NotNull BigDecimal totalLoan,
        @Schema(
                description = "Amount Paid of the loan",
                example = "1500000"
        ) @NotNull BigDecimal amountPaid,
        @Schema(
                description = "Outstanding Amount of the loan",
                example = "1500000"
        ) @NotNull BigDecimal outstandingAmount
) {
        public Loan toEntity() {
                return Loan.builder()
                        .loanNumber(this.loanNumber)
                        .amountPaid(this.amountPaid)
                        .loanType(this.type)
                        .totalLoan(this.totalLoan)
                        .amountPaid(this.amountPaid)
                        .outstandingAmount(this.outstandingAmount)
                        .build();
        }
}