package com.wangjc.task.entity.dto;

import com.wangjc.task.base.entity.dto.BaseDto;
import com.wangjc.task.entity.view.UserView;

/**
* Dto
* @author wangjc
* @date 2020-07-21 14:28:11
*/
public class UserDto extends BaseDto<UserView> {

    private static final long serialVersionUID = 1595312891532L;

    private String userAccount;

    private String password;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
