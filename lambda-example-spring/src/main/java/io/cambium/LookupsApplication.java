package io.cambium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import io.cambium.rest.LookupsController;

/**
 * LookupsApplication.
 * 
 *  Entry point into a Spring Boot application that provides options for a dropdown in a web application. 
 *
 * @author Baruch Speiser, Cambium.
 */
@SpringBootApplication
// Use direct import to reduce cold startup time
@Import({LookupsController.class})
public class LookupsApplication {

  public static void main(String[] args) {
    SpringApplication.run(LookupsApplication.class, args);
  }
}