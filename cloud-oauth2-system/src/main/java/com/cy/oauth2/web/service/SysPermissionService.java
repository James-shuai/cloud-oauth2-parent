package com.cy.oauth2.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.oauth2.web.entities.SysPermission;

import java.security.Principal;
import java.util.List;
import java.util.Map;


public interface SysPermissionService extends IService<SysPermission> {

    /**
     * @param userId 用户id
     * @return 用户所拥有的权限
     */
    List<SysPermission> findByUserId(Long userId);

  Object getNavMeny(Principal principal);

    boolean deleteRolePermission(String id);

}
