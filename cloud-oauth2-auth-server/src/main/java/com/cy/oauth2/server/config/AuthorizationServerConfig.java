package com.cy.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * 认证配置类
 *
 * @author ys
 * @date 2020/4/22 13:30
 */
@Configuration
@EnableAuthorizationServer //开启 OAuth2 认证服务器功能
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


  @Autowired
  public PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserDetailsService customUserDetailsService;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private ClientDetailsService jdbcClientDetailsService;
  //jwt转换器
  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;


  /**
   * 配置允许访问此认证服务器的客户端信息
   * 1、内存方式
   * 2、数据库方式
   *
   * @param clients
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //内存方式管理客户端信息
    /*clients
      .inMemory()
      .withClient("cloud-pc") //客户端ID
      .secret(passwordEncoder.encode("cloud-secret")) //客服端秘钥 加密
      .resourceIds("product-server") //资源服务器Id 针对微服务名称
      .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")//授权类型
      .scopes("all") //授权范围标识 那部分资源可以访问（all是标识，不是全部资源）
      .autoApprove(false) // false 跳转到授权页面手动点击授权，true 不需要手动点击授权
      .redirectUris("http://www.cy925.top/") //客户端回调地址
      .accessTokenValiditySeconds(60*60*8) //访问令牌的有效时长 默认12小时
      .refreshTokenValiditySeconds(60*60*24*60) //刷新令牌有效时长 默认30天
    ;*/

    //JDBC管理客户端
    clients.withClientDetails(jdbcClientDetailsService);
  }


  /**
   * 关于认证服务器端点配置
   *
   * @param endpoints
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    //密码模式需要这个实例
    endpoints.authenticationManager(authenticationManager);
    //刷新令牌需要使用该实例
    endpoints.userDetailsService(customUserDetailsService);
    //通过Redis管理令牌
    endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter);
  }


  /**
   * 令牌安全端点配置
   * @param security
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    //所有人可访问 /oauth/token_key 后面要获取公钥, 默认拒绝访问
    security.tokenKeyAccess("permitAll()");
    //认证后可访问 /oauth/check_token , 默认拒绝访问
    security.checkTokenAccess("isAuthenticated()");
  }
}
