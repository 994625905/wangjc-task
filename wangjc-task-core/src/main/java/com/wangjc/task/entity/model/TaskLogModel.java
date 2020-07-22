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
@TableName("t_task_log")
public class TaskLogModel extends BaseModel<TaskLogModel> {

    private static final long serialVersionUID = 1595312891566L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
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
