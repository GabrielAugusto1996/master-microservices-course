package com.eazybank.cards.controller.api;

import com.eazybank.cards.constants.CardContants;
import com.eazybank.cards.dto.CardDto;
import com.eazybank.cards.dto.ErrorResponseDto;
import com.eazybank.cards.dto.ResponseDto;
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
        name = "Cards",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH and DELETE card details"
)
@RequestMapping(path = "/api/cards", produces = {MediaType.APPLICATION_JSON_VALUE })
public interface CardApiController {

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside EazyBank",
            tags = "Cards"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = CardContants.MESSAGE_201
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
            description = CardContants.MESSAGE_201
    )
    @PostMapping(path = "", consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<ResponseDto> createCard(@Valid @RequestBody CardDto cardDto);

    @Operation(
            summary = "Fetch Card REST API",
            description = "REST API to fetch an existing Card inside EazyBank",
            tags = "Cards"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card details retrieved successfully"
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
    ResponseEntity<CardDto> fetchCardDetails(@NotBlank @RequestParam String mobileNumber);

    @Operation(
            summary = "UPDATE Card REST API",
            description = "REST API to update an existing Card inside EazyBank",
            tags = "Cards"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card details was updated successfully"
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
    ResponseEntity<CardDto> updateCard(@Valid @RequestBody CardDto cardDto,
                                                           @PathVariable String mobileNumber);

    @Operation(
            summary = "DELETE Card REST API",
            description = "REST API to delete an existing Card inside EazyBank",
            tags = "Cards"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Card was deleted successfully"
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
    ResponseEntity<Void> deleteCard(@PathVariable String mobileNumber);
}
