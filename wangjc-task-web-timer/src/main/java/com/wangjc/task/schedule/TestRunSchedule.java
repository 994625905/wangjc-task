package com.wangjc.task.schedule;

import com.wangjc.task.core.TestRunCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务：测试运行的执行器
 * @author wangjc
 * @title: TestRunSchedule
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2117:07
 */
@Component
@Async("wangjcTimerAsyncServiceExecutor")
public class TestRunSchedule extends WangjcTaskSchedule {

    private static final Logger logger = LoggerFactory.getLogger(TestRunSchedule.class);

    @Autowired
    private TestRunCore testRunCore;

    /**
     * 此处的corn表达式与数据表中的一致，变更的话需保持同步更改
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void scheduled(){
        if(this.isRun(testRunCore.TASK_SSID)){
            testRunCore.asyncRun();
            this.addLog(testRunCore.TASK_SSID);
        }
    }

}
