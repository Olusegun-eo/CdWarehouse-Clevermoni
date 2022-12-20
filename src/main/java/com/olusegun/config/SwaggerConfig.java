package com.olusegun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket cdApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cdwarehouse"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("warehouse", "Olatunji Emmanuel Olusegun", "emmanuelolusegun.co@gmail.com");
        return new ApiInfoBuilder()
                .title("CdWareHouse API")
                .description("Documentation CdWarehouse api")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }
}