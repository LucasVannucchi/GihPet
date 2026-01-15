package br.com.banhoetosa.gihpet.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "gihpet API",
                version = "v1",
                contact = @Contact(
                        name = "Lucas Vannucchi",
                        email = "lucasvannucchi16@gmail.com",
                        url = "gihpetapi.com.br"
                )
        )
)
public class OpenApiConfig {
}
