package com.eazybank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public record ResponseDto(
        @Schema(
                name = "The status code of the response",
                example = "201"
        )
        HttpStatus statusCode,
        @Schema(
                name = "The status message of the response",
                example = "Saved successfully"
        )
        String statusMessage
) {
}
