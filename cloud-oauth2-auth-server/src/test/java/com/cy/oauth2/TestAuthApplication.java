package com.cy.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthApplication {
  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  public void testPwd() {
    System.out.println(passwordEncoder.encode("mengxuegu-secret"));
  }
}
