package com.emanuele.collezioni.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Collection Manager API")
                        .version("1.0.0")
                        .description("Gestione REST di carte, oggetti e altri item di collezione"));
    }   /**
         * Configurazione del gruppo API principale
         *
         * @return l'oggetto GroupedOpenApi
         */
        @Bean
        public GroupedOpenApi apiGroup() {
            return GroupedOpenApi.builder()
                    .group("rest-api")
                    .packagesToScan("com.emanuele.collezioni.ApiRest")
                    .pathsToMatch("/api/**")
                    .build();
        }

    }


