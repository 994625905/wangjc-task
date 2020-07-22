package com.wangjc.task.entity.dto;

import com.wangjc.task.base.entity.dto.BaseDto;
import com.wangjc.task.entity.model.TaskModel;
import com.wangjc.task.entity.view.TaskView;

/**
* Dto
* @author wangjc
* @date 2020-07-21 14:28:11
*/
public class TaskDto extends BaseDto<TaskView> {

    private static final long serialVersionUID = 1595312891440L;

    /**
     * 新增项
     */
    private TaskModel model;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 验证token
     */
    private String token;

    /**
     * 字段名，字段值
     */
    private String fieldName;
    private String fieldValue;

    public TaskModel getModel() {
        return model;
    }

    public void setModel(TaskModel model) {
        this.model = model;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
