package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDetailDto(@NotBlank @Size(min = 5, max = 30) String name,
                                @NotBlank @Email String email,
                                @NotBlank @Pattern(regexp = "(^$|[0-9]{9})") String mobileNumber,
                                @NotBlank String accountType,
                                @NotBlank String branchAddress) {
}