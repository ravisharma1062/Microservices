package com.application.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RefreshScope
@EnableJpaRepositories
@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class SpringDataJpaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.application.jpa.controller"))
                .build().apiInfo(metaData());
    }

    private ApiInfo metaData() {
        List<VendorExtension> vendorExtensions = new ArrayList<>();
        VendorExtension v = new VendorExtension() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public Object getValue() {
                return null;
            }
        };
        vendorExtensions.add(v);
        ApiInfo apiInfo = new ApiInfo(
                "Spring Data JPA",
                "Demo on spring data jpa",
                "1.0",
                "https://api.themoviedb.org",
                new Contact("Ravi Sharma", "https://github.com/ravisharma1062/Microservices", "ravisharma.1062@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);
        return apiInfo;
    }

}
