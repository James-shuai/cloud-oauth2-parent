package com.cy.oauth2.web.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.oauth2.web.entities.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SysRoleMapper extends BaseMapper<SysRole> {


    @Insert("insert into sys_role_permission(role_id,permission_id) values(#{role_id},#{permission_id})")
    int insertSys_role_permission(@Param("role_id") String role_id,@Param("permission_id") String permission_id);

    @Select("select permission_id from sys_role_permission where role_id=#{role_id}")
    List<Long> getPermission_ids(@Param("role_id") String role_id);

    @Delete("delete from sys_role_permission where role_id=#{role_id}")
    int deleteSys_role_permission(@Param("role_id") Long role_id);

    @Select("select parent_id from sys_permission where id=#{id}")
    Long getParentId(@Param("id") Long id);

}
