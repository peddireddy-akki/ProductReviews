/**
 * @author Akki
 * This class is application starter class for spring boot application
 *
 */
package com.akki.productreviews;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        System.out.println("\n\n\n ProductReviewApplication Started Successfull");

    }   
}
