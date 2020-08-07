package com.cy.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import java.util.Date;

/**
 * 续签
 */
public class MyRedisTokenStoreService extends RedisTokenStore {
    @Autowired
    public ClientDetailsService jdbcClientDetailsService;


    public MyRedisTokenStoreService(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }


    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        OAuth2Authentication result = readAuthentication(token.getValue());
        if (result != null) {
            // 如果token没有失效  更新AccessToken过期时间
            DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) token;

            //重新设置过期时间
            int validitySeconds = getAccessTokenValiditySeconds(result.getOAuth2Request());
            if (validitySeconds > 0) {
                oAuth2AccessToken.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
            }

            //将重新设置过的过期时间重新存入redis, 此时会覆盖redis中原本的过期时间
            storeAccessToken(token, result);
        }
        return result;
    }

    protected int getAccessTokenValiditySeconds(OAuth2Request clientAuth) {
        //获取客户端设置的过期时间，如果没有设置 默认300s
        if (jdbcClientDetailsService != null) {
            ClientDetails client = jdbcClientDetailsService.loadClientByClientId(clientAuth.getClientId());
            Integer validity = client.getAccessTokenValiditySeconds();
            if (validity != null) {
                return validity;
            }
        }
        int accessTokenValiditySeconds = 60 * 5 ;
        return accessTokenValiditySeconds;
    }

}
