package com.wangjc.task.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wangjc.task.base.entity.view.BaseView;
import com.wangjc.task.entity.model.UserModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-07-21 14:28:11
 */
public class UserView extends BaseView<UserView> {

	private static final long serialVersionUID = 1595312891529L;

	public UserView(UserModel model) {
		this.id = model.getId();
		this.account = model.getAccount();
		this.password = model.getPassword();
	}

	public UserView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
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
