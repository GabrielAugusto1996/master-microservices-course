package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.controller.api.CustomerApiController;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerApiController {


    @Override
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber) {
        return null;
    }
}
