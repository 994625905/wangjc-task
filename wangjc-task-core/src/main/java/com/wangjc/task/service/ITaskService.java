package com.wangjc.task.service;

import com.wangjc.task.base.exception.CommonException;
import com.wangjc.task.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.entity.dto.TaskDto;
import com.wangjc.task.entity.model.TaskModel;
import com.wangjc.task.entity.view.TaskView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-07-21 14:28:11
*/
public interface ITaskService extends BaseService<TaskModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<TaskView> selectPage(TaskDto dto);

    /**
     * 任务调度--定时任务管理的控制层：激活任务
     * @param dto
     * @return
     */
    Boolean startTask(TaskDto dto) throws CommonException;

    /**
     * 任务调度--定时任务管理的控制层：停止任务
     * @param dto
     * @return
     */
    Boolean stopTask(TaskDto dto) throws CommonException;

    /**
     * 任务调度--定时任务管理的控制层：新增任务
     * @param dto
     * @return
     */
    Boolean insertTask(TaskDto dto) throws CommonException;

    /**
     * 任务调度--定时任务管理的控制层：获取所有的任务
     * @return
     */
    List<TaskView> getListAll();

    /**
     * 根据链接后缀获取指定任务
     * @param suffix
     * @return
     */
    TaskView getOneByUrlSuffix(String suffix);

    /**
     * 删除任务
     * @param dto
     * @return
     * @throws CommonException
     */
    Boolean delTask(TaskDto dto) throws CommonException;

    /**
     * 更新任务
     * @param dto
     * @return
     * @throws CommonException
     */
    Boolean editTask(TaskDto dto) throws CommonException;

}
