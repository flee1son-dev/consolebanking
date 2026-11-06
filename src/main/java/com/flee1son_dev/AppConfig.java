package com.flee1son_dev;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class AppConfig {
    @Bean 
    public Scanner scanner(){
        return new Scanner(System.in);
    }
}
