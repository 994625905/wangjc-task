package com.wangjc.task.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangjc.task.base.constant.SystemConstant;
import com.wangjc.task.base.exception.CommonException;
import com.wangjc.task.base.util.UIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.base.service.impl.BaseServiceImpl;
import com.wangjc.task.entity.dto.TaskDto;
import com.wangjc.task.entity.model.TaskModel;
import com.wangjc.task.entity.view.TaskView;
import com.wangjc.task.mapper.TaskMapper;
import com.wangjc.task.service.ITaskService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-07-21 14:28:11
*/
@Service
@Transactional
public class TaskServiceImpl extends BaseServiceImpl<TaskMapper,TaskModel> implements ITaskService {

	@Autowired
	TaskMapper mapper;

	@Override
	public IPage<TaskView> selectPage(TaskDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean insertTask(TaskDto dto) throws CommonException {
		TaskModel model = dto.getModel();
		model.setTaskToken(dto.getToken());
		model.setTaskSsid(UIDUtil.getUIDS());
		model.setCreateTime(System.currentTimeMillis()/1000L);
		Boolean res = this.save(model);
		if(!res){
			throw new CommonException("新增任务失败");
		}
		return res;
	}

	@Override
	public List<TaskView> getListAll() {
		return mapper.getListAll();
	}

	@Override
	public TaskView getOneByUrlSuffix(String suffix) {
		TaskModel taskModel = this.getOne(new QueryWrapper<TaskModel>() {{
			likeLeft("task_api", suffix);
		}});
		return taskModel == null?null:new TaskView(taskModel);
	}

	@Override
	public Boolean delTask(TaskDto dto) throws CommonException {
		int delete = this.mapper.deleteById(dto.getTaskId());
		if(delete<1){
			throw new CommonException("删除失败");
		}
		return true;
	}

	@Override
	public Boolean editTask(TaskDto dto) throws CommonException {
		TaskModel model = this.getById(dto.getTaskId());
		if("taskName".equals(dto.getFieldName())){
			model.setTaskName(dto.getFieldValue());
		}
		if("taskExplain".equals(dto.getFieldName())){
			model.setTaskExplain(dto.getFieldValue());
		}
		if("taskDate".equals(dto.getFieldName())){
			model.setTaskDate(dto.getFieldValue());
		}
		if("taskUrl".equals(dto.getFieldName())){
			model.setTaskUrl(dto.getFieldValue());
		}
		boolean res = this.modify(model);
		if(!res){
			throw new CommonException("更新失败");
		}
		return res;
	}

	@Override
	public Boolean startTask(TaskDto dto) throws CommonException {
		TaskModel model = this.getById(dto.getTaskId());
		model.setStatus(SystemConstant.SCHEDULE_TASK.STATUS.RUN);
		boolean res = this.updateById(model);
		if(!res){
			throw new CommonException("激活任务失败");
		}
		return res;
	}

	@Override
	public Boolean stopTask(TaskDto dto) throws CommonException {
		TaskModel model = this.getById(dto.getTaskId());
		model.setStatus(SystemConstant.SCHEDULE_TASK.STATUS.STOP);
		boolean res = this.updateById(model);
		if(!res){
			throw new CommonException("停止任务失败");
		}
		return res;
	}


}
