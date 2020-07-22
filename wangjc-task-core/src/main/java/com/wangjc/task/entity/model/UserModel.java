package com.wangjc.task.entity.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wangjc.task.base.entity.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* Model
* @author wangjc
* @date 2020-07-21 14:28:11
*/
@TableName("t_user")
public class UserModel extends BaseModel<UserModel> {

    private static final long serialVersionUID = 1595312891526L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 
    */
    @TableField("account")
    private String account;

    /**
    * 
    */
    @TableField("password")
    private String password;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setAccount(String account) {
    this.account = account;
    }

    public String getAccount() {
    return this.account;
    }

    public void setPassword(String password) {
    this.password = password;
    }

    public String getPassword() {
    return this.password;
    }

}
