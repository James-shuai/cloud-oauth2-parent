package com.cy.oauth2.resources.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.cors.CorsUtils;

/**
 * 资源服务器配置类
 *
 * @author ys
 * @date 2020/4/23 9:53
 */
@Configuration
@EnableResourceServer //标识资源服务器 请求服务资源需带Token
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法级别权限控制
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  public static final String RESOURCE_ID = "product-server";
  /**
   * 密码加密
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Autowired
  private TokenStore tokenStore;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources
      .resourceId(RESOURCE_ID) //资源服务器ID，认证服务端判断是否有该资源服务id的权限
//      .tokenServices(tokenService())//远程认证服务器校验token
      .tokenStore(tokenStore) //校验token合法性
    ;
  }


  /*public ResourceServerTokenServices tokenService(){
    //远程认证服务器校验token是否有效
    RemoteTokenServices service = new RemoteTokenServices();
    //请求认证服务器校验地址
    service.setCheckTokenEndpointUrl("http://localhost:8090/auth/oauth/check_token");
    //客户端Id
    service.setClientId("mengxuegu-pc");
    //客服端秘钥
    service.setClientSecret("mengxuegu-secret");
    return service;
  }*/

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      //跨域配置开始GlobalCorsConfig
      .cors().disable()
      .cors()
      .and()
      .authorizeRequests()
      .requestMatchers(CorsUtils::isPreFlightRequest)
      .permitAll().and()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS)
      .permitAll().and()
      //跨域配置结束
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//spring security 不会使用也不会创建session实例
      .and()
      .authorizeRequests()
      //所有请求都需要有 all 范围
      .antMatchers("/**").access("#oauth2.hasAnyScope('SYSTEM_API')")
      .antMatchers("/system/weChat/**").permitAll()
    ;
  }
}
