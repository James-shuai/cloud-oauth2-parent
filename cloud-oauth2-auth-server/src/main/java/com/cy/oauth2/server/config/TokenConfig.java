package com.cy.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 使用Redis管理令牌
 * @author ys
 * @date 2020/4/22 15:06
 */
@Configuration
public class TokenConfig {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;



  @Bean
  public TokenStore tokenStore(){
//    RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//    tokenStore.setSerializationStrategy(new JdkSerializationStrategy());
//    return tokenStore;
    //通过Redis管理令牌
//    return new RedisTokenStore(redisConnectionFactory);
//    //通过JWT 管理令牌
    return new JwtTokenStore(jwtAccessTokenConverter);
  }


}
