package com.example.data.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api-Profile")
                .apiInfo(apiProfile()).select().paths(PathSelectors.regex("/api.*")).build();
    }

    private ApiInfo apiProfile() {
        return new ApiInfoBuilder()
                .title("sbc order api")
                .description("sbc order api")
                .termsOfServiceUrl("http://localhost:8080")
                .contact(new Contact("Ildar", "https://github.com/wheelbarrowru", "otvertka1213@gmail.com"))
                .version("1.0.0")
                .build();
    }
}
