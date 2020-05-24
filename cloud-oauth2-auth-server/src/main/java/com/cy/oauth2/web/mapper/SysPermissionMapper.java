package com.cy.oauth2.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.oauth2.web.entities.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID获取拥有资源权限
     * @param userId 用户id
     * @return 
     */
    List<SysPermission> findByUserId(@Param("userId") Long userId);
}
