package com.eazybytes.accounts.controller.api;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(
        name = "Customers",
        description = "CRUD REST APIs in EazyBank to FETCH customer details"
)
@RequestMapping(path = "/api/customers", produces = {MediaType.APPLICATION_JSON_VALUE })
public interface CustomerApiController {

    @Operation(
            summary = "Fetch Customer REST API",
            description = "REST API to fetch an existing Customer inside EazyBank",
            tags = "Customers"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer details retrieved successfully"
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
    ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@NotBlank @RequestParam String mobileNumber);
}
