package com.example.ruleengine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author alexouyang
 * 2019-07-25
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.ruleengine.apis"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dynamic Rule Engine Demo")
                .description("演示Java动态规则引擎的基础能力\n年龄规则定义：30岁以下符合要求\n " +
                        "性别规则定义：只有男性符合要求\n 且年龄规则优先级高于性别规则\n薪水规则定义(只在动态规则演示中):低于1000元才符合要求")
                .version("1.0")
                .build();
    }

}