package com.cy.oauth2.web.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class SysPermission implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父资源id,给它初始值 0
     * 新增和修改页面上默认的父资源id
     */
    private Long parentId = 0L ;
    private String title;

    @TableField("`index`")
    private String index;
    private String code;
    /**
     * 菜单：1，按钮：2
     */
    private Integer type;
    private Integer sort;
    private String icon;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 所有子权限对象集合
     * 左侧菜单渲染时要用
     */
    @TableField(exist = false)
    private List<SysPermission> children;


}
