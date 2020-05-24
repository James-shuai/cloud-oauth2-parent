package com.cy.oauth2.web.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.oauth2.web.entities.SysPermission;
import com.cy.oauth2.web.mapper.SysPermissionMapper;
import com.cy.oauth2.web.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Override
    public List<SysPermission> findByUserId(Long userId) {
        if(userId == null) {
            return null;
        }
        List<SysPermission> list = baseMapper.findByUserId(userId);
        //用户无任何权限时，list会有一个 `null` 空的SysPermission 对象，把那个null移除
        list.remove(null);
        return list;
    }
}
