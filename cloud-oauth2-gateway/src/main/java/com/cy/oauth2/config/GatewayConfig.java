package com.cy.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域
 * @author ys
 * @date 2020/4/23 17:11
 */
@Configuration
public class GatewayConfig {

  // 配置全局解决zuul服务中的cors跨域问题
  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedMethod("*");
  //↓核心代码
    corsConfiguration.addExposedHeader("Authorization");
    source.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(source);
  }



}
