package com.cy.oauth2.resources.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 获取网关转发过来的请求头中token信息
 * @author ys
 * @date 2020/4/23 17:15
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //获取token
    String authToken = request.getHeader("auth-token");
    if (StringUtils.isNotEmpty(authToken)){
      //通过base64解码
      String authTokenJson = new String(Base64Utils.decodeFromString(authToken));
      //转成json对象
      JSONObject jsonObject = JSON.parseObject(authTokenJson);
      //用户信息（用户名）
      Object principal = jsonObject.get("principal");
      //请求详情
      Object details = jsonObject.get("details");
      //用户权限
      String authorities = ArrayUtils.toString(jsonObject.getJSONArray("authorities").toArray());
      List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
      //手动构建一个 UsernamePasswordAuthenticationToken 对象 SpringSecurity 会自动进行权限判断
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,null,grantedAuthorities);
      authenticationToken.setDetails(details);

      //将对象传给安全上下文 自动判断权限 同时也可以获取用户信息
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    //放行请求
    filterChain.doFilter(request,response);
  }
}
