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
@TableName("t_task")
public class TaskModel extends BaseModel<TaskModel> {

    private static final long serialVersionUID = 1595312891253L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 任务名称
    */
    @TableField("task_name")
    private String taskName;

    /**
    * 唯一标识
    */
    @TableField("task_ssid")
    private String taskSsid;

    /**
    * 任务说明
    */
    @TableField("task_explain")
    private String taskExplain;

    /**
    * 是否允许手动触发：0不允许，1允许
    */
    @TableField("allow_do")
    private Integer allowDo;

    /**
    * 0自动，1手动
    */
    @TableField("auto_do")
    private Integer autoDo;

    /**
    * 任务链接：提供手动触发的接口
    */
    @TableField("task_url")
    private String taskUrl;

    /**
    * 验证token
    */
    @TableField("task_token")
    private String taskToken;

    /**
    * 执行时间
    */
    @TableField("task_date")
    private String taskDate;

    /**
    * 调用是否存在参数，0不存在，1存在（一般泛指时间跨度）
    */
    @TableField("is_param")
    private Integer isParam;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 0停机，1正常
    */
    @TableField("status")
    private Integer status;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setTaskName(String taskName) {
    this.taskName = taskName;
    }

    public String getTaskName() {
    return this.taskName;
    }

    public void setTaskSsid(String taskSsid) {
    this.taskSsid = taskSsid;
    }

    public String getTaskSsid() {
    return this.taskSsid;
    }

    public void setTaskExplain(String taskExplain) {
    this.taskExplain = taskExplain;
    }

    public String getTaskExplain() {
    return this.taskExplain;
    }

    public void setAllowDo(Integer allowDo) {
    this.allowDo = allowDo;
    }

    public Integer getAllowDo() {
    return this.allowDo;
    }

    public void setAutoDo(Integer autoDo) {
    this.autoDo = autoDo;
    }

    public Integer getAutoDo() {
    return this.autoDo;
    }

    public void setTaskUrl(String taskUrl) {
    this.taskUrl = taskUrl;
    }

    public String getTaskUrl() {
    return this.taskUrl;
    }

    public void setTaskToken(String taskToken) {
    this.taskToken = taskToken;
    }

    public String getTaskToken() {
    return this.taskToken;
    }

    public void setTaskDate(String taskDate) {
    this.taskDate = taskDate;
    }

    public String getTaskDate() {
    return this.taskDate;
    }

    public void setIsParam(Integer isParam) {
    this.isParam = isParam;
    }

    public Integer getIsParam() {
    return this.isParam;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setStatus(Integer status) {
    this.status = status;
    }

    public Integer getStatus() {
    return this.status;
    }

}
