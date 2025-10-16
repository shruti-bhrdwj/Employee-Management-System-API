package com.parkinpro.ems.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Employee Management System REST API",
        version = "1.0.0",
        description = "EMS Portal to manage employees of a company",
        contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Shruti Sharma")
    )
)
public class SwaggerConfig {
	// http://localhost:8080/swagger-ui/index.html
	// http://localhost:8080/api-docs
}

