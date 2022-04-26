package ru.mipt.data.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class for configuration swagger-ui in web application
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * This method creates swagger-ui web application
     * @return Docket
     */
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("API-Profile-Data")
                .apiInfo(apiProfile()).select().paths(PathSelectors.any()).build();
    }

    /**
     * Method for setting parameters and links in Profile in swagger-ui
     * @return ApiInfoBuilder
     */
    private ApiInfo apiProfile() {
        return new ApiInfoBuilder()
                .title("uFitness API")
                .description("Profile Controller data API")
                .termsOfServiceUrl("http://localhost:8080")
                .contact(new Contact("Ildar Khabibulin", "https://github.com/wheelbarrowru", "khabibulin_ildarr@mail.ru"))
                .version("1.3.0")
                .build();
    }
}
