package com.eazybytes.accounts.dto;

import org.springframework.http.HttpStatus;

public record ResponseDto(HttpStatus statusCode, String statusMessage) {
}
