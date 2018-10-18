/**
 * @author Akki
 * This class is application starter class for spring boot application
 *
 */
package com.akki;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		System.out.println("\n\n\n ProductReviewApplication Started Successfull");

	}
	
	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}
}
