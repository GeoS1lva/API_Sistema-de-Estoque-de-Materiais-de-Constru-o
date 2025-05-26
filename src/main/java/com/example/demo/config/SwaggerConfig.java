/*
 * Geovana Paula da Silva RA 170610-2024
*/

package com.example.demo.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/api/**")
                .packagesToScan("com.example.demo")
                .addOpenApiMethodFilter(method -> method.getDeclaringClass().isAnnotationPresent(RestController.class))
                .addOpenApiCustomizer(customOpenApi())
                .build();
    }

    public OpenApiCustomizer customOpenApi() {
        return openApi -> {
            openApi.getInfo().setTitle("Sistema de Estoque de Materiais de Construção");
            openApi.getInfo().setVersion("1.0.0");
            openApi.getInfo().setDescription("API de gerenciamento de Estoque.");
        };
    }
}