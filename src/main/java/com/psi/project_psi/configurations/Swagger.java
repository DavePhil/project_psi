package com.psi.project_psi.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
    info = @Info(
            contact = @Contact(
                    name = "Dave CHEDJOUN",
                    email = "davechedjoun@gmail.com"
    ),
            title = " Documentation for Pan-african Space Industry (PSI)",
            description = "All routes to resources of the api of PSI with their description",
            version = "1.0"
),
        servers = {
            @Server(
                    description = "local env",
                    url = "http://localhost:8081/"
            ),
            @Server(
                    description = "prod env",
                    url = "https://app.panafrican-space.net/"
            )
        }
)
@SecurityRequirement(
        name = "bearerAuth"
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class Swagger {


}
