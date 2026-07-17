package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.controller.api.CustomerApiController;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomerApiController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber, String correlationId) {

        logger.debug("fetchCustomerDetails method start");
        CustomerDetailsDto customerDetailsDto = this.customerService.fetchCustomerDetails(mobileNumber);
        logger.debug("fetchCustomerDetails method end");

        return ResponseEntity.ok(customerDetailsDto);
    }
}
