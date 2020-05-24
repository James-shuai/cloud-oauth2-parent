package com.cy.oauth2.web.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.oauth2.web.entities.SysUser;
import com.cy.oauth2.web.mapper.SysUserMapper;
import com.cy.oauth2.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        // baseMapper 对应的是 SysUserMapper 实例
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public SysUser findByMobile(String mobile) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        // baseMapper 对应的是 SysUserMapper 实例
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional
    public boolean saveUser(SysUser sysUser) {
        sysUser.setAccountNonExpired(true);
        sysUser.setCredentialsNonExpired(true);
        sysUser.setEnabled(true);
        sysUser.setCreateDate(new Date());
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        int isOk = baseMapper.insert(sysUser);
        List<Long> roleIds = sysUser.getRoleIds();
        for (Long t:roleIds) {
            isOk+=sysUserMapper.insertsys_user_role(sysUser.getId(),t);
        }
        if (isOk>0){
            return true;
        }
        return false;
    }

    @Override
    public SysUser getUserInfo(Long id) {
        SysUser sysUser = baseMapper.selectById(id);
        List<Long> longs = sysUserMapper.roleIdList(sysUser.getId());
        sysUser.setRoleIds(longs);
        return sysUser;
    }

    @Override
    @Transactional
    public boolean editUser(SysUser sysUser) {
        sysUser.setUpdateDate(new Date());
        int isOk = baseMapper.updateById(sysUser);
        isOk+=sysUserMapper.deletesys_user_role(sysUser.getId());
        List<Long> roleIds = sysUser.getRoleIds();
        for (Long t:roleIds) {
            isOk+=sysUserMapper.insertsys_user_role(sysUser.getId(),t);
        }
        if (isOk>0){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean delUser(Long id) {
        SysUser sysUser = baseMapper.selectOne(new QueryWrapper<SysUser>().eq("id", id));
        sysUser.setEnabled(false);
        int isOk = baseMapper.updateById(sysUser);
        isOk+=sysUserMapper.deletesys_user_role(id);
        if (isOk>0){
            return true;
        }
        return false;
    }

}
