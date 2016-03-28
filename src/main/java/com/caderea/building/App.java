package com.caderea.building;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Cade on 3/27/2016.
 */
@ComponentScan
@EnableAutoConfiguration
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
