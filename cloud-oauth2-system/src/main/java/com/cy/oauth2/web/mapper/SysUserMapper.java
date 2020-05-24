package com.cy.oauth2.web.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy.oauth2.web.entities.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SysUserMapper extends BaseMapper<SysUser> {

    @Insert("insert into sys_user_role(user_id,role_id) values(#{user_id},#{role_id})")
    int insertsys_user_role(@Param("user_id") Long user_id,@Param("role_id") Long role_id);

    @Select("select role_id from sys_user_role where user_id=#{user_id}")
    List<Long> roleIdList(@Param("user_id") Long user_id);

    @Delete("delete from sys_user_role where user_id=#{user_id}")
    int deletesys_user_role(@Param("user_id") Long user_id);


}
