package com.amg.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@EntityScan(basePackages = {"com"})
@ServletComponentScan

@SpringBootApplication
@ComponentScan("com")
public class RestApplication {


    public static void main(String[] args) {



        SpringApplication.run(RestApplication.class, args);
    }

}
