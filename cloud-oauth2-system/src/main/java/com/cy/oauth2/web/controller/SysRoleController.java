package com.cy.oauth2.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy.oauth2.web.entities.SysRole;
import com.cy.oauth2.web.service.SysRoleService;
import com.cy.base.result.ResultData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/getRoleList")
    public Object getRoleList(@RequestBody String request){
        JSONObject jsonObject = JSON.parseObject(request);
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        if (jsonObject.get("roleName")!=null&& StringUtils.isNotBlank(jsonObject.get("roleName").toString())){
            sysRoleQueryWrapper.like("name",jsonObject.get("roleName").toString());
        }
        sysRoleQueryWrapper.orderByDesc("create_date");
        IPage<SysRole> page = sysRoleService.page(new Page(Integer.parseInt(jsonObject.get("page").toString()),Integer.parseInt(jsonObject.get("limit").toString()),true),sysRoleQueryWrapper);
        return ResultData.ok(page);
    }

    @RequestMapping("/getRole")
    public Object getRole(){
        List<SysRole> list = sysRoleService.list();
        return ResultData.ok(list);
    }

    @RequestMapping("/saveRole")
    @PreAuthorize("hasAnyAuthority('sys:role:add')")
    public Object saveRole(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            if (sysRoleService.saveRole(jsonObject)){
                return ResultData.ok("操作成功");
            }
            return ResultData.build(300,"操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("角色保存异常："+e.getMessage());
            return ResultData.build(403,"操作异常");
        }
    }


    @RequestMapping("/getRoleInfo")
    public Object getRoleInfo(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            SysRole roleInfo = sysRoleService.getRoleInfo(jsonObject);
            if (roleInfo!=null){
                return ResultData.ok(roleInfo);
            }
            return ResultData.build(300,"操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("角色查单条异常："+e.getMessage());
            return ResultData.build(403,"操作异常");
        }
    }

    @RequestMapping("/editRole")
    @PreAuthorize("hasAnyAuthority('sys:role:edit')")
    public Object editRole(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            if (sysRoleService.editRole(jsonObject)){
                return ResultData.ok("操作成功");
            }
            return ResultData.build(300,"操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("角色修改异常："+e.getMessage());
            return ResultData.build(403,"操作异常");
        }
    }

    @RequestMapping("/delRole")
    @PreAuthorize("hasAnyAuthority('sys:role:del')")
    public Object delRole(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            if (sysRoleService.delRole(jsonObject)){
                return ResultData.ok("操作成功");
            }
            return ResultData.build(300,"操作失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("角色删除异常："+e.getMessage());
            return ResultData.build(403,"操作异常");
        }
    }
    @RequestMapping("/checkRoleName")
    public Object checkRoleName(@RequestBody String request){
        try {
            JSONObject jsonObject = JSON.parseObject(request);
            QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
            sysRoleQueryWrapper.eq("name", jsonObject.getString("name"));
            if (jsonObject.get("id")!=null&&StringUtils.isNotBlank(jsonObject.get("id").toString())){
                sysRoleQueryWrapper.ne("id", jsonObject.getString("id"));
            }
            int count = sysRoleService.count(sysRoleQueryWrapper);
            if (count==0){
                return ResultData.ok();
            }
            return ResultData.build(401,"角色名称重复");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("角色删除异常："+e.getMessage());
            return ResultData.build(403,"操作异常");
        }
    }


}
