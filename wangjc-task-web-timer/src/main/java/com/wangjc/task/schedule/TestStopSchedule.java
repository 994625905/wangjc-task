package com.wangjc.task.schedule;

import com.wangjc.task.core.TestStopCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务：测试终止的执行器
 * @author wangjc
 * @title: TestStopSchedule
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2117:13
 */
@Component
@Async("wangjcTimerAsyncServiceExecutor")
public class TestStopSchedule extends WangjcTaskSchedule{

    private static final Logger logger = LoggerFactory.getLogger(TestStopSchedule.class);

    @Autowired
    private TestStopCore testStopCore;

    /**
     * 此处的corn表达式与数据表中的一致，变更的话需保持同步更改
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void scheduled(){
        if(this.isRun(testStopCore.TASK_SSID)){
            testStopCore.asyncRun();
            this.addLog(testStopCore.TASK_SSID);
        }
    }

}
