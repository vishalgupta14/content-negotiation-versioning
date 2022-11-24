package com.springboot.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "com.springboot.rest.controller";
    private static final String SEARCH = "spring-rest-swagger";
    private static final String GROUP_NAME_VERSION_DEFAULT = "version";
    private static final String GROUP_NAME_VERSION_V1 = "version-v1";

    @Bean
    public Docket swaggerDocketDefault() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME_VERSION_DEFAULT)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .apis(p -> {
                    if (null != p.produces()) {
                        for (MediaType mt : p.produces()) {
                            if (mt.toString().equals("application/json")) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .build()
                .produces(Collections.singleton("application/json"))
                .apiInfo(apiInfo());

    }

    @Bean
    public Docket swaggerDocketV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(GROUP_NAME_VERSION_V1)
                .select()
                .apis(p -> {
                    if (null != p.produces()) {
                        for (MediaType mt : p.produces()) {
                            if (mt.toString().equals("application/vnd.version-v1+json")) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .build()
                .produces(Collections.singleton("application/vnd.version-v1+json"))
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(SEARCH).build();
    }

}
