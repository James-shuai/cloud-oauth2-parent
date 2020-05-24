package com.cy.oauth2.server.service;


import com.cy.oauth2.web.entities.SysUser;
import com.cy.oauth2.web.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("mobileUserDetailsService") // 一定不要少了
public class MobileUserDetailsService extends AbstractUserDetailsService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;

    @Override
    SysUser findSysUser(String mobile){
        logger.info("请求认证的手机号：" + mobile);
        return sysUserService.findByMobile(mobile);
    }
}
