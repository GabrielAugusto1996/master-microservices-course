package com.eazybytes.accounts.controller.api;

import com.eazybytes.accounts.constants.AccountContants;
import com.eazybytes.accounts.dto.CustomerDetailDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(
        name = "Accounts",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH and DELETE account details"
)
@RequestMapping(path = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE })
public interface AccountsApiController {

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside EazyBank",
            tags = "Accounts"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = AccountContants.MESSAGE_201
            )
    })
    @ApiResponse(
            responseCode = "201",
            description = AccountContants.MESSAGE_201
    )
    @PostMapping(path = "", consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto);

    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch an existing Customer & Account inside EazyBank",
            tags = "Accounts"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details retrieved successfully"
            )
    })
    @GetMapping("/fetch")
    ResponseEntity<CustomerDto> fetchAccountDetails(@NotBlank @RequestParam String mobileNumber);

    @Operation(
            summary = "UPDATE Account REST API",
            description = "REST API to update an existing Customer & Account inside EazyBank",
            tags = "Accounts"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details was updated successfully"
            )
    })
    @PutMapping(path = "{accountNumber}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<CustomerDetailDto> updateAccount(@Valid @RequestBody CustomerDetailDto customerDetailDto,
                                                           @PathVariable Long accountNumber);

    @Operation(
            summary = "DELETE Account REST API",
            description = "REST API to delete an existing Customer & Account inside EazyBank",
            tags = "Accounts"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Account was deleted successfully"
            )
    })
    @DeleteMapping(path = "{accountNumber}")
    ResponseEntity<Void> deleteAccount(@PathVariable Long accountNumber);
}
