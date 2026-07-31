package com.eazybytes.accounts.service.client;

import com.eazybytes.accounts.dto.LoanDto;
import com.eazybytes.accounts.service.client.fallback.LoansFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(value = "loans", fallback = LoansFallback.class) Applied for Eureka Server
@FeignClient(value = "loans", url = "http://loans:8090", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<LoanDto> fetchLoansDetails(String mobileNumber);
}