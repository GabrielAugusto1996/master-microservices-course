package com.eazybank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "The Error Response details"
)
public record ErrorResponseDto(
        @Schema(
                name = "The api path",
                example = "/api/v1/accounts"
        )
        String apiPath,
        @Schema(
                name = "The error code",
                example = "500"
        )
        HttpStatus errorCode,
        @Schema(
                name = "The error message",
                example = "Failed to retrieve the account details"
        )
        String errorMessage,
        @Schema(
                name = "The error time",
                example = "2025-03-03T15:30:25"
        )
        LocalDateTime errorTime
) {
}
