package com.wiss.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EONET Backend API")
                        .version("1.0.0")
                        .description("REST API für Earth Natural Events Tracker mit CRUD-Operations für Events")
                        .contact(new Contact()
                                .name("Natascha")
                                .email("natascha.blumer@wiss-edu.ch")));
    }
}
