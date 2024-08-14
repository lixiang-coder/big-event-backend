package com.xzy.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        log.info("Swagger 接口文档开始生成");
        return new OpenAPI()
                .info(new Info()
                        .title("大事件接口文档")
                        .description("接口文档的描述信息")
                        .version("版本信息：v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("描述")
                        .url("/"));
    }
}
