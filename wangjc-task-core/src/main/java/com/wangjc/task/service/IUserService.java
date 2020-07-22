package com.wangjc.task.service;

import com.wangjc.task.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.entity.dto.UserDto;
import com.wangjc.task.entity.model.UserModel;
import com.wangjc.task.entity.view.UserView;

/**
* Service
* @author wangjc
* @date 2020-07-21 14:28:11
*/
public interface IUserService extends BaseService<UserModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserView> selectPage(UserDto dto);

}
