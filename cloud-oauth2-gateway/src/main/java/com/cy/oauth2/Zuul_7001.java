package com.cy.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author ys
 * @date 2020/4/23 16:27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class Zuul_7001 {

  public static void main(String[] args) {
    SpringApplication.run(Zuul_7001.class,args);
  }

}
