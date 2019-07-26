package com.example.ruleengine;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sun.tools.jar.CommandLine;

import java.util.Arrays;

@SpringBootApplication
public class DynamicRuleEngineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicRuleEngineDemoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context){
//        return args -> {
//            System.out.println("Spring Boot 管理的所有bean:");
//            String[] beanNames = context.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//        };
//    }

}
