package com.wangjc.task.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangjc.task.base.constant.SystemConstant;
import com.wangjc.task.base.controller.BaseController;
import com.wangjc.task.base.util.CookieUtil;
import com.wangjc.task.entity.model.TaskLogModel;
import com.wangjc.task.entity.model.TaskModel;
import com.wangjc.task.entity.model.UserModel;
import com.wangjc.task.entity.view.UserView;
import com.wangjc.task.service.ITaskLogService;
import com.wangjc.task.service.ITaskService;
import com.wangjc.task.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * report下所有controller的基类
 * @author wangjc
 * @title: WebBaseController
 * @projectName i4-dp
 * @description: TODO
 * @date 2019/9/1213:37
 */
public class WebBaseController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(WebBaseController.class);
    private final static String CURR_USER = "USER";

    @Autowired
    protected RedisService redisService;
    @Autowired
    private ITaskService iTaskService;
    @Autowired
    private ITaskLogService iTaskLogService;

    /**
     * 获取当前登录的用户
     * @return
     */
    public UserModel getCurrentUser(HttpServletRequest request){
        String token = CookieUtil.getCookieValue(request,"token");
        return (UserModel) redisService.hget(SystemConstant.REDIS_KEY.LOGIN,token);
    }

    /**
     * 获取当前登录用户的id
     * @param request
     * @return
     */
    public Integer getUserId(HttpServletRequest request){
        return this.getCurrentUser(request).getId();
    }

    /**
     * 获取模型视图，统一摄入user and theme
     * @param viewName
     * @param request
     * @return
     */
    public ModelAndView getModelAndView(String viewName,HttpServletRequest request){
        ModelAndView view = super.getModelAndView(viewName);
        view.addObject(CURR_USER, JSON.toJSONString(new UserView(this.getCurrentUser(request))));
        return view;
    }

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
            setRunType(SystemConstant.SCHEDULE_TASK.RUN_TYPE.HAND);
            setRunTime(System.currentTimeMillis() / 1000L);
            setTaskid(taskModel.getId());
            setSuccess(SystemConstant.SCHEDULE_TASK.RUN_RESULT.SUCCESS);
        }});
        if(insert > 0){
            logger.info("[{}]:定时任务已执行一次",taskModel.getTaskName());
        }
        return insert;
    }


}
