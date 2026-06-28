package com.eazybank.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "EazyBank Loans microservice REST API Documentation",
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
				description = "External EazyBank Loans microservice REST API Documentation",
				url = "https://github.com/GabrielAugusto1996/master-microservices-course"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
