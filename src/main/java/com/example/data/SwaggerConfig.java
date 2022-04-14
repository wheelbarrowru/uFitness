//package com.example.data;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket newsApi() {
//        return new Docket(DocumentationType.SWAGGER_2).groupName("api-videogames")
//                .apiInfo(apiVideogames()).select().paths(PathSelectors.regex("/api.*")).build();
//    }
//
//    private ApiInfo apiVideogames() {
//        return new ApiInfoBuilder().title("Videogames REST api POC")
//                .description("PoC of a REST api, to test both Springboot and Swagger")
//                .termsOfServiceUrl("https://creativecommons.org/licenses/by/4.0/")
//                .contact("abcd@mail.com")
//                .license("GNU General Public License v3.0").licenseUrl(
//                        "https://www.gnu.org/licenses/gpl-3.0.en.html").version("3.0").build();
//    }
//}
