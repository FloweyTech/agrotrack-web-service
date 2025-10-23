package com.floweytech.agrotrack.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    // General Info
    @Value("${documentation.application.title}")
    private String applicationTitle;

    @Value("${documentation.application.description}")
    private String applicationDescription;

    @Value("${documentation.application.version}")
    private String applicationVersion;

    @Bean
    public OpenAPI agrotrackPlatformOpenAPI() {

        // Configure license information
        var license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        // Configure API information
        var info = new Info()
                .title(applicationTitle)
                .description(applicationDescription)
                .version(applicationVersion)
                .license(license);

        return new OpenAPI()
                .openapi("3.0.1")
                .info(info);
    }
}