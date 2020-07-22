package com.wangjc.task.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.base.service.impl.BaseServiceImpl;
import com.wangjc.task.entity.dto.TaskLogDto;
import com.wangjc.task.entity.model.TaskLogModel;
import com.wangjc.task.entity.view.TaskLogView;
import com.wangjc.task.mapper.TaskLogMapper;
import com.wangjc.task.service.ITaskLogService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-07-21 14:28:11
*/
@Service
@Transactional
public class TaskLogServiceImpl extends BaseServiceImpl<TaskLogMapper,TaskLogModel> implements ITaskLogService {

	@Autowired
	TaskLogMapper mapper;

	@Override
	public IPage<TaskLogView> selectPage(TaskLogDto dto) {
    	return mapper.selectPage(dto);
    }

}
