package com.cy.oauth2.web.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.oauth2.web.entities.SysRole;
import com.cy.oauth2.web.entities.SysUser;
import com.cy.oauth2.web.mapper.SysRoleMapper;
import com.cy.oauth2.web.mapper.SysUserMapper;
import com.cy.oauth2.web.service.SysRoleService;
import com.cy.oauth2.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional
    public boolean saveRole(JSONObject jsonObject) {
        SysRole sysRole = new SysRole();
        sysRole.setName(jsonObject.get("name").toString());
        sysRole.setRemark(jsonObject.get("remark").toString());
        sysRole.setCreateDate(new Date());
        int insert = sysRoleMapper.insert(sysRole);
        JSONArray treeNode = JSON.parseArray(jsonObject.get("treeNode").toString());
        for (int i = 0; i < treeNode.size(); i++) {
            insert+=sysRoleMapper.insertSys_role_permission(sysRole.getId().toString(),treeNode.get(i).toString());
        }
        if (insert>1){
            return true;
        }
        return false;
    }

    @Override
    public SysRole getRoleInfo(JSONObject jsonObject) {
        SysRole sysRole = sysRoleMapper.selectOne(new QueryWrapper<SysRole>().eq("id", jsonObject.get("id")));
        List<Long> permission_ids = sysRoleMapper.getPermission_ids(sysRole.getId().toString());
        List<Long> tempList= new ArrayList<>();
        for (Long t:permission_ids) {
            Long parentId = sysRoleMapper.getParentId(t);
            tempList.add(parentId);
        }
        List<Long> temp = receiveDefectList(permission_ids, tempList);


        sysRole.setPerIds(temp);

        return sysRole;
    }

    @Override
    @Transactional
    public boolean editRole(JSONObject jsonObject) {
        SysRole sysRole = new SysRole();
        sysRole.setId(Long.parseLong(jsonObject.get("id").toString()));
        sysRole.setName(jsonObject.get("name").toString());
        sysRole.setRemark(jsonObject.get("remark").toString());
        sysRole.setUpdateDate(new Date());
        int isOk = sysRoleMapper.updateById(sysRole);
        isOk = sysRoleMapper.deleteSys_role_permission(sysRole.getId());
        JSONArray treeNode = JSON.parseArray(jsonObject.get("treeNode").toString());
        for (int i = 0; i < treeNode.size(); i++) {
            isOk+=sysRoleMapper.insertSys_role_permission(sysRole.getId().toString(),treeNode.get(i).toString());
        }
        if (isOk>1){
            return true;
        }
        return false;
    }


    /**
     * @方法描述：获取两个ArrayList的差集
     * @param firstArrayList 第一个ArrayList
     * @param secondArrayList 第二个ArrayList
     * @return resultList 差集ArrayList
     */
    public static List<Long> receiveDefectList(List<Long> firstArrayList, List<Long> secondArrayList) {
        List<Long> resultList = new ArrayList<>();
        LinkedList<Long> result = new LinkedList<>(firstArrayList);// 大集合用linkedlist
        HashSet<Long> othHash = new HashSet<>(secondArrayList);// 小集合用hashset
        Iterator<Long> iter = result.iterator();// 采用Iterator迭代器进行数据的操作
        while(iter.hasNext()){
            if(othHash.contains(iter.next())){
                iter.remove();
            }
        }
        resultList = new ArrayList<>(result);
        return resultList;
    }

    @Override
    @Transactional
    public boolean delRole(JSONObject jsonObject) {
        int isOK = sysRoleMapper.deleteById(Long.parseLong(jsonObject.get("id").toString()));
        isOK+=sysRoleMapper.deleteSys_role_permission(Long.parseLong(jsonObject.get("id").toString()));
        if (isOK>0){
            return true;
        }
        return false;
    }
}
