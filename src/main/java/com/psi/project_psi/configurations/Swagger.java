package com.psi.project_psi.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

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
            )
        }

)
public class Swagger {

}
