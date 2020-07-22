package com.wangjc.task.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangjc.task.base.constant.SystemConstant;
import com.wangjc.task.entity.model.TaskLogModel;
import com.wangjc.task.entity.model.TaskModel;
import com.wangjc.task.service.ITaskLogService;
import com.wangjc.task.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangjc
 * @title: WangjcTaskSchedule
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2117:04
 */
public class WangjcTaskSchedule {

    private static final Logger logger = LoggerFactory.getLogger(WangjcTaskSchedule.class);

    @Autowired
    private ITaskService iTaskService;
    @Autowired
    private ITaskLogService iTaskLogService;

    /**
     * 根据唯一标识判断是否该任务是否运行
     * @param taskSsId
     * @return
     */
    public Boolean isRun(String taskSsId){
        TaskModel taskModel = iTaskService.getOne(new QueryWrapper<TaskModel>() {{
            eq("task_ssid", taskSsId);
        }});
        if(taskModel == null){
            logger.info("定时任务已经不存在");
            return false;
        }
        if(taskModel.getStatus() == SystemConstant.SCHEDULE_TASK.STATUS.RUN){
            return true;
        }
        logger.info("[{}]:定时任务已手动停机",taskModel.getTaskName());
        return false;
    }

    /**
     * 新增一条执行记录
     * @param taskSsId
     * @return
     */
    public Integer addLog(String taskSsId){
        TaskModel taskModel = iTaskService.getOne(new QueryWrapper<TaskModel>() {{
            eq("task_ssid", taskSsId);
        }});
        int insert = iTaskLogService.getBaseMapper().insert(new TaskLogModel() {{
            setRunType(SystemConstant.SCHEDULE_TASK.RUN_TYPE.AUTO);
            setRunTime(System.currentTimeMillis() / 1000L);
            setTaskid(taskModel.getId());
            setSuccess(SystemConstant.SCHEDULE_TASK.RUN_RESULT.SUCCESS);
        }});
        if(insert > 0){
            logger.info("[{}]:定时任务已执行一次",taskModel.getTaskName());
        }
        return insert;
    }

    /**
     * 获取任务执行时间
     * @param taskSsId
     * @return
     */
    public String getTaskDate(String taskSsId){
        TaskModel taskModel = iTaskService.getOne(new QueryWrapper<TaskModel>() {{
            eq("task_ssid", taskSsId);
        }});
        if(taskModel == null){
            return null;
        }
        return taskModel.getTaskDate();
    }


}
