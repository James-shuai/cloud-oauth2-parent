package com.cy.oauth2.server.authentication;

import com.cy.base.result.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器
 * 1、决定响应json还是跳转页面 或者是认证成功后的其他处理
 * @author ys
 * @date 2020/4/10 11:00
 */
@Component("customAuthenticationSuccessHandler")
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  Logger logger = LoggerFactory.getLogger(getClass());



  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    ResultData result = ResultData.ok("认证成功");
    httpServletResponse.setContentType("application/json;charset=UTF-8");
    httpServletResponse.getOutputStream().write(result.toJsonString().getBytes("UTF-8"));

  }
}
