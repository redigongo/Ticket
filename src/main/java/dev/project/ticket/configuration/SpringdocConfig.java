package dev.project.ticket.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {

    @Bean
    public GroupedOpenApi myApi() {
        final String[] packagesToScan = {"dev.project.ticket.controllers"};
        return GroupedOpenApi.builder()
                .group("TICKET Api")
                .packagesToScan(packagesToScan)
                .pathsToMatch("/TICKET/**")
                .build();
    }

}