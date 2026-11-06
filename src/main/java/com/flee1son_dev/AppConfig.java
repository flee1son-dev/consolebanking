package com.flee1son_dev;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration 
@ComponentScan("com.flee1son_dev")
@PropertySource("classpath:application.properties")
public class AppConfig {
    
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer = 
            new PropertySourcesPlaceholderConfigurer();
    
    @Bean 
    public Scanner scanner(){
        return new Scanner(System.in);
    }
}