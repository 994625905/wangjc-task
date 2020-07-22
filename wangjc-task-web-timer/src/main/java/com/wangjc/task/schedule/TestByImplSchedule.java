package com.wangjc.task.schedule;

import com.wangjc.task.core.TestByImplCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * 定时任务：测试实现SchedulingConfigurer来动态注入规则的执行器
 * @author wangjc
 * @title: TestByImpl
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2214:27
 */
@Configuration
public class TestByImplSchedule extends WangjcTaskSchedule implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(TestByImplSchedule.class);

    @Autowired
    private TestByImplCore testByImplCore;

    /**
     * 重写方法，设置任务和触发
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(doTask(),getTrigger());
    }

    /**
     * 核心业务代码，这里是单独开辟一个子线程来执行业务
     * @return
     */
    public Runnable doTask() {
        return new Runnable() {
            @Override
            public void run() {
                if(isRun(testByImplCore.TASK_SSID)){
                    testByImplCore.asyncRun();
                    addLog(testByImplCore.TASK_SSID);
                }
            }
        };
    }

    /**
     * 触发器
     * @return
     */
    private Trigger getTrigger() {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                String taskDate = getTaskDate(testByImplCore.TASK_SSID);
                logger.info("测试实现SchedulingConfigurer来动态注入规则：[{}]",taskDate);

                CronTrigger trigger = new CronTrigger(taskDate);
                return trigger.nextExecutionTime(triggerContext);
            }
        };
    }

}
