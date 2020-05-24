package com.cy.oauth2.resources.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yin .
 * @Date Created Date: 2019/3/21 14:25
 * @Description Note:
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class CORSFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    response.setHeader("Access-Control-Allow-Origin","*");
    response.setHeader("Access-Control-Allow-Credentials","true");
    response.setHeader("Access-Control-Allow-Methods","POST,GET");
    response.setHeader("Access-Control-Allow-Max-Age","3600");
    response.setHeader("Access-Control-Allow-Headers","*");
    if("OPTIONS".equalsIgnoreCase(request.getMethod())){
      response.setStatus(HttpServletResponse.SC_OK);
    }else{
      filterChain.doFilter(servletRequest,servletResponse);
    }
  }

  @Override
  public void destroy() {

  }
}
