package com.cy.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ys
 * @date 2020/4/23 15:48
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka_6001 {

  public static void main(String[] args) {
    SpringApplication.run(Eureka_6001.class,args);
  }

}
