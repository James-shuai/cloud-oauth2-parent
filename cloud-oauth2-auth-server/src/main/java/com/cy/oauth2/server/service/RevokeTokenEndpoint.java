package com.cy.oauth2.server.service;

import com.cy.base.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/authentication")
public class RevokeTokenEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;


    @RequestMapping("/oauth/logOut")
    public Object revokeToken(HttpServletRequest request) {
        String access_token = request.getParameter("access_token");
        if (consumerTokenServices.revokeToken(access_token)){
            return ResultData.ok("注销成功");
        }else{
            return ResultData.build(401,"注销失败");
        }
    }


}
