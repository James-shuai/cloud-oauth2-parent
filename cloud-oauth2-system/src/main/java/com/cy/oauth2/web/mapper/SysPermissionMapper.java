package com.cy.oauth2.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.oauth2.web.entities.SysPermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID获取拥有资源权限
     * @param userId 用户id
     * @return
     */
    List<SysPermission> findByUserId(@Param("userId") Long userId);

  @Select("select distinct sp.* from sys_user su\n" +
    "left join sys_user_role sur on su.id=sur.user_id\n" +
    "left join sys_role sr on sr.id=sur.role_id\n" +
    "left join sys_role_permission srp on srp.role_id=sr.id\n" +
    "left join sys_permission sp on sp.id=srp.permission_id\n" +
    "where sp.type=1 and su.id=#{userid}")
  List<SysPermission> findByUserIds(@Param("userid") Long userid);

  @Delete("delete sys_role_permission where permission_id=#{id}")
    int deleteRolePermission(@Param("id") String id);

}
