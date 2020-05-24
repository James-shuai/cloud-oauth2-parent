package com.cy.oauth2.web.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.oauth2.web.entities.SysRole;
import com.cy.oauth2.web.entities.SysUser;

public interface SysRoleService extends IService<SysRole> {

    boolean saveRole(JSONObject jsonObject);
    SysRole getRoleInfo(JSONObject jsonObject);

    boolean editRole(JSONObject jsonObject);

    boolean delRole(JSONObject jsonObject);

}
