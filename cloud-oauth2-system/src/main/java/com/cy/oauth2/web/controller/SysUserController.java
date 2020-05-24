package com.cy.oauth2.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.oauth2.web.entities.SysRole;
import com.cy.oauth2.web.entities.SysUser;
import com.cy.oauth2.web.service.SysUserService;
import com.cy.base.result.ResultData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class SysUserController {
    Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/getUserList")
    public Object getUserList(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            QueryWrapper<SysUser> sysRoleQueryWrapper = new QueryWrapper<>();
            if (jsonObject.get("username")!=null&& StringUtils.isNotBlank(jsonObject.get("username").toString())){
                sysRoleQueryWrapper.like("username",jsonObject.get("username").toString());
            }
            if (jsonObject.get("nickName")!=null&& StringUtils.isNotBlank(jsonObject.get("nickName").toString())){
                sysRoleQueryWrapper.like("nick_name",jsonObject.get("nickName").toString());
            }
            if (jsonObject.get("mobile")!=null&& StringUtils.isNotBlank(jsonObject.get("mobile").toString())){
                sysRoleQueryWrapper.like("mobile",jsonObject.get("mobile").toString());
            }
            sysRoleQueryWrapper.eq("is_enabled",1);
            sysRoleQueryWrapper.orderByDesc("create_date");
            IPage<SysRole> page = sysUserService.page(new Page(Integer.parseInt(jsonObject.get("page").toString()),Integer.parseInt(jsonObject.get("limit").toString()),true),sysRoleQueryWrapper);
            return ResultData.ok(page);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("获取用户列表异常："+e.getMessage());
            return ResultData.build(403,"获取列表异常");
        }
    }

    @RequestMapping("/saveUser")
    @PreAuthorize("hasAnyAuthority('sys:user:add')")
    public Object saveUser(@RequestBody SysUser sysUser){
        try {
            boolean result = sysUserService.saveUser(sysUser);
            if (result){
                return ResultData.ok("操作成功");
            }
            return ResultData.build(403,"操作失败");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("新增用户异常："+e.getMessage());
            return ResultData.build(403,"获取列表异常");
        }
    }
    @RequestMapping("/getUserInfo")
    public Object getUserInfo(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            SysUser sysUser = sysUserService.getUserInfo(Long.parseLong(jsonObject.getString("id")));
            sysUser.setPassword(null);
            if (sysUser!=null){
                return ResultData.ok(sysUser);
            }
            return ResultData.build(403,"操作失败");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("用户单条异常："+e.getMessage());
            return ResultData.build(403,"用户单条异常");
        }
    }

    @RequestMapping("/editUser")
    @PreAuthorize("hasAnyAuthority('sys:user:edit')")
    public Object editUser(@RequestBody SysUser sysUser){
        try {
            boolean result = sysUserService.editUser(sysUser);
            if (result){
                return ResultData.ok("操作成功");
            }
            return ResultData.build(403,"操作失败");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("修改用户异常："+e.getMessage());
            return ResultData.build(403,"修改用户异常");
        }
    }

    @RequestMapping("/editisAccountNonLockedUser")
    @PreAuthorize("hasAnyAuthority('sys:user:switch')")
    public Object editisAccountNonLockedUser(@RequestBody SysUser sysUser){
        try {
            boolean result = sysUserService.updateById(sysUser);
            if (result){
                return ResultData.ok("操作成功");
            }
            return ResultData.build(403,"操作失败");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("修改用户状态异常："+e.getMessage());
            return ResultData.build(403,"修改用户异常");
        }
    }

    @RequestMapping("/delUser")
    @PreAuthorize("hasAnyAuthority('sys:user:del')")
    public Object delUser(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            boolean result = sysUserService.delUser(Long.parseLong(jsonObject.getString("id")));
            if (result){
                return ResultData.ok("操作成功");
            }
            return ResultData.build(403,"操作失败");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("删除用户异常："+e.getMessage());
            return ResultData.build(403,"删除用户异常");
        }
    }

    @RequestMapping("/checkUsername")
    public Object checkUsername(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
            sysUserQueryWrapper.eq("username", jsonObject.getString("username"));
            if (jsonObject.get("id")!=null&&StringUtils.isNotBlank(jsonObject.get("id").toString())){
                sysUserQueryWrapper.ne("id", jsonObject.getString("id"));
            }
            int count = sysUserService.count(sysUserQueryWrapper);
            if (count==0){
                return ResultData.ok();
            }
            return ResultData.build(403,"用户名重复");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("删除用户异常："+e.getMessage());
            return ResultData.build(403,"删除用户异常");
        }
    }

}
