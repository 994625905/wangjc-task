package com.wangjc.task.web.msg;

import com.wangjc.task.base.exception.CommonException;
import com.wangjc.task.base.result.ActionResult;
import com.wangjc.task.base.util.JWTUtil;
import com.wangjc.task.entity.dto.TaskDto;
import com.wangjc.task.entity.view.TaskView;
import com.wangjc.task.service.ITaskService;
import com.wangjc.task.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务调度--定时任务管理的控制层
 * @author wangjc
 * @title: ScheduleMsgController
 * @projectName i4-dp
 * @description: TODO
 * @date 2020/5/209:03
 */
@Controller
@RequestMapping(value = "/taskMsg")
public class TaskMsgController extends WebBaseController {

    @Autowired
    private ITaskService iTaskService;

    /**
     * 加载模块主页
     * @param request
     */
    @RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request){
        ModelAndView view = getModelAndView("/task/taskMsg_index", request);

        List<TaskView> list = iTaskService.getListAll();
        view.addObject("list",list);
        return view;
    }

    /**
     * 新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/insertPage",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView insertPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/task/taskMsg_insert", request);
        return view;
    }

    /**
     * 新增任务
     * @param dto
     * @return
     */
    @RequestMapping(value = "/insertTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<Boolean> insertTask(@RequestBody TaskDto dto, HttpServletRequest request){
        dto.setToken(JWTUtil.sign(getUserId(request),getCurrentUser(request).getAccount()));
        try {
            return ActionResult.ok(iTaskService.insertTask(dto));
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 激活任务
     * @param dto
     * @return
     */
    @RequestMapping(value = "/startTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<Boolean> startTask(TaskDto dto){
        try {
            return ActionResult.ok(iTaskService.startTask(dto));
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 停止任务
     * @param dto
     * @return
     */
    @RequestMapping(value = "/stopTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<Boolean> stopTask(TaskDto dto){
        try {
            return ActionResult.ok(iTaskService.stopTask(dto));
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 删除任务
     * @param dto
     * @return
     */
    @RequestMapping(value = "/delTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<Boolean> delTask(TaskDto dto){
        try {
            return ActionResult.ok(iTaskService.delTask(dto));
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 编辑任务
     * @param dto
     * @return
     */
    @RequestMapping(value = "/editTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<Boolean> editTask(TaskDto dto){
        try {
            return ActionResult.ok(iTaskService.editTask(dto));
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

}
