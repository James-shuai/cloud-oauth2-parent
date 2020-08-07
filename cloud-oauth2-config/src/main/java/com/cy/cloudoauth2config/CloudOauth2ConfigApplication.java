package com.cy.cloudoauth2config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudOauth2ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudOauth2ConfigApplication.class, args);
    }

}
