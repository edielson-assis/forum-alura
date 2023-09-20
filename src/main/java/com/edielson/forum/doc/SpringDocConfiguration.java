package com.edielson.forum.doc;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Fórum Alura API")
                        .description("API Rest da aplicação Fórum Alura. A API permite aos usuários criar, atualizar, listar e excluir tópicos de discussão relacionados aos cursos em que estão inscritos.")
                        .contact(new Contact()
                                .name("Edielson Assis")
                                .url("https://www.linkedin.com/in/edielson-assis"))
                        .license(new License()
                                .name("Licença MIT")
                                .url("https://github.com/edielson-assis/forum-alura/blob/main/LICENSE")));
    }
}