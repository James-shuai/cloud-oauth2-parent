package com.cy.oauth2.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.oauth2.web.entities.SysUser;


public interface SysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUsername(String username);

    /**
     * 通过手机号查询
     * @param mobile 手机号
     * @return 用户信息
     */
    SysUser findByMobile(String mobile);

}
