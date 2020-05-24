package com.cy.oauth2.server.authentication;

import com.cy.base.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author ys
 * @date 2020/4/10 11:17
 */
@Component("customAuthenticationFailureHandler")
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    //1、认证失败后响应json字符串
    ResultData result = ResultData.build(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.getOutputStream().write(result.toJsonString().getBytes("UTF-8"));

  }
}
