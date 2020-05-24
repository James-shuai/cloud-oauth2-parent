package com.cy.oauth2.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy.oauth2.web.entities.SysPermission;
import com.cy.oauth2.web.service.SysPermissionService;
import com.cy.base.result.ResultData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ys
 * @date 2020/4/27 13:45
 */
@RestController
@RequestMapping("/system/menu")
public class SysPermissionController {

  Logger logger = LoggerFactory.getLogger(SysPermissionController.class);

  @Autowired
  private SysPermissionService sysPermissionService;




  @RequestMapping("/getNavMenu")
  public Object getNavMenu(Principal principal){
    return sysPermissionService.getNavMeny(principal);
  }


  @RequestMapping("/getMenu")
  public Object getMenu(@RequestBody String request){
    try {
      JSONObject jsonObject = JSON.parseObject(request);
      QueryWrapper<SysPermission> type = new QueryWrapper<SysPermission>();
      if (jsonObject.get("type")!=null&&StringUtils.isNotBlank(jsonObject.get("type").toString())){
        type.eq("type", jsonObject.get("type").toString());
      }
      if (jsonObject.get("menuName")!=null&&StringUtils.isNotBlank(jsonObject.get("menuName").toString())){
        type.like("title", jsonObject.get("menuName").toString());
      }
      List<SysPermission> list = sysPermissionService.list(type);
      List<SysPermission> menuTreeList = getMenuTreeList(list, "0");
      return ResultData.ok(menuTreeList);
    } catch (Exception e) {
      e.printStackTrace();
      return ResultData.build(500,"菜单控制器："+e.getMessage());
    }
  }

  @RequestMapping("/getOptionMenu")
  public Object getOptionMenu(){
    try {
      List<SysPermission> list = sysPermissionService.list(new QueryWrapper<SysPermission>().eq("type", 1));
      SysPermission sysPermission = new SysPermission();
      sysPermission.setParentId(0L);
      sysPermission.setTitle("请选择");
      sysPermission.setId(0L);
      list.add(0,sysPermission);
      return ResultData.ok(list);
    } catch (Exception e) {
      e.printStackTrace();
      return ResultData.build(500,"菜单控制器："+e.getMessage());
    }
  }


  @RequestMapping("/saveMenu")
  @PreAuthorize("hasAnyAuthority('sys:menu:add')")
  public Object saveMenu(@RequestBody String request){
    try {
      JSONObject jsonObject = JSON.parseObject(request);
      SysPermission sysPermission = new SysPermission();
      sysPermission.setIndex(jsonObject.get("index").toString());
      sysPermission.setCode(jsonObject.get("code").toString());
      sysPermission.setType(Integer.parseInt(jsonObject.get("type").toString()));
      sysPermission.setTitle(jsonObject.get("menuName").toString());
      sysPermission.setRemark(jsonObject.get("remark").toString());
      sysPermission.setSort(Integer.parseInt(jsonObject.get("sort").toString()));
      sysPermission.setCreateDate(new Date());
      if (jsonObject.get("parentMenu")!=null&&StringUtils.isNotBlank(jsonObject.get("parentMenu").toString())){
        sysPermission.setParentId(Long.parseLong(jsonObject.get("parentMenu").toString()));
      }else {
        sysPermission.setParentId(0L);
      }
      boolean save = sysPermissionService.save(sysPermission);
      if (save){
        return ResultData.ok("操作成功");
      }
      return ResultData.build(500,"操作失败");
    } catch (Exception e) {
      e.printStackTrace();
      return ResultData.build(500,"菜单控制器："+e.getMessage());
    }
  }


  @RequestMapping("/getMenuInfo")
  public Object getMenuInfo(@RequestBody String request){
    try {
      JSONObject jsonObject = JSON.parseObject(request);
      SysPermission sysPermission = sysPermissionService.getOne(new QueryWrapper<SysPermission>().eq("id", jsonObject.get("id").toString()));
      return ResultData.ok(sysPermission);
    } catch (Exception e) {
      e.printStackTrace();
      return ResultData.build(500,"菜单控制器："+e.getMessage());
    }
  }


  @RequestMapping("/delMenu")
  @PreAuthorize("hasAnyAuthority('sys:menu:del')")
  public Object delMenu(@RequestBody String request){
    try {
      JSONObject jsonObject = JSON.parseObject(request);
      int count = sysPermissionService.count(new QueryWrapper<SysPermission>().eq("parent_id", jsonObject.get("id").toString()));
      if (count==0){
        boolean remove = sysPermissionService.deleteRolePermission(jsonObject.get("id").toString());
        if (remove){
          return ResultData.ok("删除成功");
        }
        return ResultData.build(302,"删除失败");
      }
      return ResultData.build(302,"该目录或菜单下有子集，请先删除子集再操作");

    } catch (Exception e) {
      e.printStackTrace();
      return ResultData.build(500,"菜单控制器："+e.getMessage());
    }
  }


  @RequestMapping("/editMenu")
  @PreAuthorize("hasAnyAuthority('sys:menu:edit')")
  public Object editMenu(@RequestBody String request){
    try {
      JSONObject jsonObject = JSON.parseObject(request);
      SysPermission sysPermission = new SysPermission();
      sysPermission.setIndex(jsonObject.get("index").toString());
      sysPermission.setId(Long.parseLong(jsonObject.get("id").toString()));
      sysPermission.setCode(jsonObject.get("code").toString());
      sysPermission.setType(Integer.parseInt(jsonObject.get("type").toString()));
      sysPermission.setTitle(jsonObject.get("menuName").toString());
      sysPermission.setRemark(jsonObject.get("remark").toString());
      sysPermission.setSort(Integer.parseInt(jsonObject.get("sort").toString()));
      sysPermission.setCreateDate(new Date());
      if (jsonObject.get("parentMenu")!=null&&StringUtils.isNotBlank(jsonObject.get("parentMenu").toString())){
        sysPermission.setParentId(Long.parseLong(jsonObject.get("parentMenu").toString()));
      }else {
        sysPermission.setParentId(0L);
      }
      boolean save = sysPermissionService.update(sysPermission,new QueryWrapper<SysPermission>().eq("id", sysPermission.getId()));
      if (save){
        return ResultData.ok("操作成功");
      }
      return ResultData.build(500,"操作失败");
    } catch (Exception e) {
      e.printStackTrace();
      return ResultData.build(500,"菜单控制器："+e.getMessage());
    }
  }


  private List<SysPermission> getMenuTreeList(List<SysPermission> menuList, String parentID) {
    // 查找所有菜单
    List<SysPermission> childrenList = new ArrayList<>();
    menuList.stream()
            .filter(d -> String.valueOf(d.getParentId()).equals(parentID)).sorted(Comparator.comparing(SysPermission::getSort))
            .collect(Collectors.toList())
            .forEach(d -> {
              d.setChildren(getMenuTreeList(menuList,String.valueOf(d.getId())));
              childrenList.add(d);
            });
    return childrenList;
  }

}
