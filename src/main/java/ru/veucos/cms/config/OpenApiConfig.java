package ru.veucos.cms.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "authorization", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
/**
 * Конфигурация Open API Swagger
 */
public class OpenApiConfig {
    /**
     * @param appDescription Описание приложения
     * @param appVersion     Версия приложения
     * @return
     */
    @Bean
    public OpenAPI customOpenAPI(@Value("${service.description}") String appDescription,
                                 @Value("${service.version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Система учета и расчета кредита")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
