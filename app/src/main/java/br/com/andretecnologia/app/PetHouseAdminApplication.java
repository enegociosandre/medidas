package br.com.andretecnologia.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PetHouseAdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PetHouseAdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PetHouseAdminApplication.class);
    }
}
