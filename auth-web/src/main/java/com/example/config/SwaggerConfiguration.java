package com.example.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author jiangqiangqiang
 * @description: swagger3 api配置
 * @date 2021/11/20 3:14 下午
 */
@Configuration
@EnableOpenApi // 开启swagger自定义接口文档
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerApi3() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("3.X")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 基础信息
     *
     * @return /
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("xiaoen-dubbo title")
                .description("接口文档")
                .termsOfServiceUrl("")
                .contact(new Contact("xiaoen", "", "2933294631@qq.com"))
                .version("1.0.0")
                .build();
    }
}
