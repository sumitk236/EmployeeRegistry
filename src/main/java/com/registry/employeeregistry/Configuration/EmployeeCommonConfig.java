package com.registry.employeeregistry.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
public class EmployeeCommonConfig {

    /**
     * @DESCRIPTION This is a EmployeeCommonConfig with swagger configuration details
     * @METHODS swaggerConfiguration
     * @AUTHENTICATION Basic AUth
     * */

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .apis(RequestHandlerSelectors.basePackage("com.registry.employeeregistry"))
                .build()
                .apiInfo(apiDetails());
    }

/*    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
        return () -> {
            SwaggerResource wsResource = new SwaggerResource();
            wsResource.setName("Documentation");
            wsResource.setSwaggerVersion("2.0");
            wsResource.setLocation("/swagger.yaml");

            List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
            resources.add(wsResource);
            return resources;
        };
    }*/

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Employee Directory API",
                "Sample API for Employee Directory",
                "1.0",
                "Free To Use",
                new Contact("Sumit kumar","http://localhost:8081","Sumitk236@gmail.com"),
                "API License",
                "http://localhost:8081",
                Collections.emptyList());
    }

    /*RetryTemplate Configuration bean*/

    @Bean
    public RetryTemplate retryTemplate(){
        SimpleRetryPolicy policy=new SimpleRetryPolicy();
        policy.setMaxAttempts(3);

        FixedBackOffPolicy backOffPolicy=new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(3000);

        RetryTemplate retryTemplate=new RetryTemplate();
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
