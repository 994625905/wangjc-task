package com.wangjc.task.service;

import com.wangjc.task.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.entity.dto.TaskLogDto;
import com.wangjc.task.entity.model.TaskLogModel;
import com.wangjc.task.entity.view.TaskLogView;

/**
* Service
* @author wangjc
* @date 2020-07-21 14:28:11
*/
public interface ITaskLogService extends BaseService<TaskLogModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<TaskLogView> selectPage(TaskLogDto dto);

}
