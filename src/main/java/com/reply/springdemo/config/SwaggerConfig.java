package com.reply.springdemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi groupedOpenApi(){
        String[] paths = {"/**"};
        String[] packagesToScan = {"com.reply.springdemo.controller"};

        return GroupedOpenApi.builder()
                .group("demoSpring")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().components(new Components()
                .addSecuritySchemes("basic", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .scheme("basic")))
                .addSecurityItem(new SecurityRequirement());
    }
}
