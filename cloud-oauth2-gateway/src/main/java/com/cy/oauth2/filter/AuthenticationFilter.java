package com.cy.oauth2.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author ys
 * @date 2020/4/23 16:52
 */
@Component
public class AuthenticationFilter extends ZuulFilter {
  @Override
  public String filterType() {
    return PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //如果解析到令牌就会封装到 OAuth2Authentication 中
    if (!(authentication instanceof OAuth2Authentication)) {
      return null;
    }
    //用户名 没有其他信息
    Object principal = authentication.getPrincipal();
    //获取用户所拥有的权限
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
    //请求详情
    Object details = authentication.getDetails();
    Map<String,Object> result = new HashMap<>();
    result.put("principal",principal);
    result.put("authorities",authoritySet);
    result.put("details",details);

    //获取当前请求上下文
    RequestContext context = RequestContext.getCurrentContext();
    //将用户信息与权限转成json 再通过base64编码
    String resultBase64 = Base64Utils.encodeToString(JSON.toJSONString(result).getBytes());
    //添加到请求头
    context.addZuulRequestHeader("auth-token",resultBase64);

    return null;
  }
}
