package com.cy.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.cors.CorsUtils;

/**
 * 当前类用于管理所有的资源（认证服务器，资源服务器）
 * @author ys
 * @date 2020/4/23 16:36
 */
@Configuration
public class ResourceServerConfig {


  public static final String RESOURCE_ID = "product-server";

  @Autowired
  private TokenStore tokenStore;


  /**
   * 认证资源服务器的资源
   */
  @EnableResourceServer
  @Configuration
  public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      resources
        .resourceId(RESOURCE_ID) //资源服务器ID，认证服务端判断是否有该资源服务id的权限
        .tokenStore(tokenStore) //校验token合法性
      ;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.cors().and().authorizeRequests()
        .anyRequest().permitAll();//认证服务器 所有请求全部放行
    }

  }


  /**
   * 商品资源服务器的资源
   */
  @EnableResourceServer
  @Configuration
  public class ProductResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      resources
        .resourceId(RESOURCE_ID) //资源服务器ID，认证服务端判断是否有该资源服务id的权限
        .tokenStore(tokenStore) //校验token合法性
      ;
    }
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
        .antMatchers("/product/**").access("#oauth2.hasScope('PRODUCT_PAI')");
    }

  }


  /**
   * 商品资源服务器的资源
   */
  @EnableResourceServer
  @Configuration
  public class SystemResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
      resources
        .resourceId(RESOURCE_ID) //资源服务器ID，认证服务端判断是否有该资源服务id的权限
        .tokenStore(tokenStore) //校验token合法性
      ;
    }
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
        .antMatchers("/system/**").access("#oauth2.hasScope('SYSTEM_API')")
              .antMatchers("/system/weChat/**").permitAll()
      ;
    }

  }

}
