package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "EazyBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Gabriel Augusto Morato dos Santos",
						url = "https://github.com/GabrielAugusto1996/master-microservices-course",
						email = "master.dev@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/GabrielAugusto1996/master-microservices-course"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "External EazyBank Accounts microservice REST API Documentation",
				url = "https://github.com/GabrielAugusto1996/master-microservices-course"
		)
)
@EnableConfigurationProperties(value = {AccountContactInfoDto.class })
@EnableFeignClients
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
