package com.eazybank.loans.controller.api;

import com.eazybank.loans.constants.LoanContants;
import com.eazybank.loans.dto.LoanDto;
import com.eazybank.loans.dto.ErrorResponseDto;
import com.eazybank.loans.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
        name = "Loans",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH and DELETE loan details"
)
@RequestMapping(path = "/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE })
public interface LoanApiController {

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new Loan inside EazyBank",
            tags = "Loans"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = LoanContants.MESSAGE_201
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD Request Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @ApiResponse(
            responseCode = "201",
            description = LoanContants.MESSAGE_201
    )
    @PostMapping(path = "", consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ResponseDto> createLoan(@Valid @RequestBody LoanDto loanDto);

    @Operation(
            summary = "Fetch Loan REST API",
            description = "REST API to fetch an existing Loan inside EazyBank",
            tags = "Loans"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Loan details retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    ResponseEntity<LoanDto> fetchLoanDetails(@NotBlank @RequestParam String mobileNumber);

    @Operation(
            summary = "UPDATE Loan REST API",
            description = "REST API to update an existing Loan inside EazyBank",
            tags = "Loans"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Loan details was updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD Request Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping(path = "{mobileNumber}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<LoanDto> updateLoan(@Valid @RequestBody LoanDto loanDto,
                                       @PathVariable String mobileNumber);

    @Operation(
            summary = "DELETE Loan REST API",
            description = "REST API to delete an existing Loan inside EazyBank",
            tags = "Loans"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Loan was deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD Request Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Resource Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping(path = "{mobileNumber}")
    ResponseEntity<Void> deleteLoan(@PathVariable String mobileNumber);
}
