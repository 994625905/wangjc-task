package com.wangjc.task.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.wangjc.task.base.entity.view.BaseView;
import com.wangjc.task.entity.model.TaskLogModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-07-21 14:28:11
 */
public class TaskLogView extends BaseView<TaskLogView> {

	private static final long serialVersionUID = 1595312891569L;

	public TaskLogView(TaskLogModel model) {
		this.id = model.getId();
		this.taskid = model.getTaskid();
		this.runType = model.getRunType();
		this.runTime = model.getRunTime();
		this.success = model.getSuccess();
	}

	public TaskLogView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 定时任务id
	 */
	@TableField("taskid")
	private Integer taskid;

	/**
	 * 运行类型，0自动，1手动
	 */
	@TableField("run_type")
	private Integer runType;

	/**
	 * 运行时间（手动运行的）
	 */
	@TableField("run_time")
	private Long runTime;

	/**
	 * 0失败，1成功
	 */
	@TableField("success")
	private Integer success;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTaskid(Integer taskid) {
		this.taskid = taskid;
	}

	public Integer getTaskid() {
		return this.taskid;
	}

	public void setRunType(Integer runType) {
		this.runType = runType;
	}

	public Integer getRunType() {
		return this.runType;
	}

	public void setRunTime(Long runTime) {
		this.runTime = runTime;
	}

	public Long getRunTime() {
		return this.runTime;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getSuccess() {
		return this.success;
	}

}
