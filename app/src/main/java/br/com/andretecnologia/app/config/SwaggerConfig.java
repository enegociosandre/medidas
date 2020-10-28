package br.com.andretecnologia.app.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.andretecnologia.app.controller"))
                .paths(regex("/*.*"))
                .build()
                .apiInfo(new ApiInfo(
                        "Pet House Admin API",
                        "Backend",
                        "0.1.0",
                        "Terms of service",
                        new Contact("André", "https://github.com/andretecnologia", "enegocios.andre@gmail.com"),
                        "", "", Collections.emptyList()));
    }
}
