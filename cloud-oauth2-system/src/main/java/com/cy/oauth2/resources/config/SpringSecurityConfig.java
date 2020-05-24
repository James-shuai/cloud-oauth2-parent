package com.cy.oauth2.resources.config;//package com.mengxuegu.oauth2.resources.config;
//
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author ys
// * @date 2020/4/23 16:48
// */
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  /**
//   * 当前所有请求全部放行，交给资源配置类进行资源权限判断
//   * @param http
//   * @throws Exception
//   */
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests().anyRequest().permitAll();
//  }
//
//
//}
