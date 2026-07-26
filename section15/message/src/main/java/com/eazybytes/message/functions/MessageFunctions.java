package com.eazybytes.message.functions;

import com.eazybytes.message.dto.AccountMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * The type Message functions.
 */
@Configuration
public class MessageFunctions {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    /**
     * Email function.
     *
     * @return the function
     */
    @Bean
    public Function<AccountMessageDto, AccountMessageDto> email() {
        return  accountMessageDto -> {
            logger.info("Sending email for accounting to the email: {}", accountMessageDto.email());

            return accountMessageDto;
        };
    }

    /**
     * Sms function.
     *
     * @return the function
     */
    @Bean
    public Function<AccountMessageDto, Long> sms() {
        return  accountMessageDto -> {
            logger.info("Sending sms for accounting to the mobile number: {}", accountMessageDto.mobileNumber());

            return accountMessageDto.accountNumber();
        };
    }
}
