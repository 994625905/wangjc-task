package com.wangjc.task.web.hand;

import com.wangjc.task.base.result.ActionResult;
import com.wangjc.task.core.TestByImplCore;
import com.wangjc.task.core.TestRunCore;
import com.wangjc.task.core.TestStopCore;
import com.wangjc.task.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 提供手动执行的接口
 * @author wangjc
 * @title: TaskHandController
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2117:20
 */
@RestController
@RequestMapping(value = "/taskHand")
public class TaskHandController extends WebBaseController {

    @Autowired
    private TestStopCore testStopCore;
    @Autowired
    private TestRunCore testRunCore;
    @Autowired
    private TestByImplCore testByImplCore;

    /**
     * 测试执行任务
     * @return
     */
    @RequestMapping(value = "/testRun",method = {RequestMethod.GET,RequestMethod.POST})
    public ActionResult<Boolean> testRun(){

        if(this.isRun(testRunCore.TASK_SSID)){
            testRunCore.asyncRun();
            this.addLog(testRunCore.TASK_SSID);
            return ActionResult.ok(true);
        }
        return ActionResult.error("该任务已被手动停机");
    }

    /**
     * 测试终止任务
     * @return
     */
    @RequestMapping(value = "/testStop",method = {RequestMethod.GET,RequestMethod.POST})
    public ActionResult<Boolean> testStop(@RequestBody Map<String,Object> map){
        if(this.isRun(testStopCore.TASK_SSID)){
            testStopCore.asyncRun(map);
            this.addLog(testStopCore.TASK_SSID);
            return ActionResult.ok(true);
        }
        return ActionResult.error("该任务已被手动停机");
    }

    /**
     * 测试实现SchedulingConfigurer接口
     * @return
     */
    @RequestMapping(value = "/testImpl",method = {RequestMethod.GET,RequestMethod.POST})
    public ActionResult<Boolean> testImpl(){
        if(this.isRun(testByImplCore.TASK_SSID)){
            testByImplCore.asyncRun();
            this.addLog(testByImplCore.TASK_SSID);
            return ActionResult.ok(true);
        }
        return ActionResult.error("该任务已被手动停机");
    }

}
