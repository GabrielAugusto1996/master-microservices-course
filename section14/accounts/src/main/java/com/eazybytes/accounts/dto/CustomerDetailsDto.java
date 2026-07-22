package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(
        name = "Customer Details",
        description = "Schema to hold Customer, Account, Cards and Loans information"
)
@ToString @Getter @Setter @Builder
public class CustomerDetailsDto {
    @Schema(
            description = "Name of the customer",
            example = "John Doe"
    )
    @NotBlank
    @Size(min = 5, max = 30)
    private String name;

    @Schema(
            description = "Email of the customer",
            example = "john.doe@gmail.com"
    )
    @NotBlank
    @Email
    private String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "491903725"
    )
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer",
            implementation = AccountDto.class
    )
    private AccountDto accountDto;

    @Schema(
            description = "Card details of the Customer",
            implementation = CardDto.class
    )
    private CardDto cardDto;

    @Schema(
            description = "Loan details of the Customer",
            implementation = LoanDto.class
    )
    private LoanDto loanDto;
}
