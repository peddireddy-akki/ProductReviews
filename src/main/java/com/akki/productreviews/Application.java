/**
 * @author Akki
 * This class is application starter class for spring boot application
 *
 */
package com.akki.productreviews;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LogManager.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        System.out.println("\n\n\n ProductReviewApplication Started Successfull");
        
        LOGGER.debug("Log4j issue");

    }   
}
