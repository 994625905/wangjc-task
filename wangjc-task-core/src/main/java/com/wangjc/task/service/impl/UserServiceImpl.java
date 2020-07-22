package com.wangjc.task.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.base.service.impl.BaseServiceImpl;
import com.wangjc.task.entity.dto.UserDto;
import com.wangjc.task.entity.model.UserModel;
import com.wangjc.task.entity.view.UserView;
import com.wangjc.task.mapper.UserMapper;
import com.wangjc.task.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-07-21 14:28:11
*/
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper,UserModel> implements IUserService {

	@Autowired
	UserMapper mapper;

	@Override
	public IPage<UserView> selectPage(UserDto dto) {
    	return mapper.selectPage(dto);
    }

}
