package com.wangjc.task.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务：测试执行的业务编写
 * @author wangjc
 * @title: TestStart
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2116:59
 */
@Component
public class TestRunCore {

    private static final Logger logger = LoggerFactory.getLogger(TestRunCore.class);
    /** 任务的唯一标识 */
    public final static String TASK_SSID = "3df834c5-13d6-415b-a149-ac6b56f40f41";

    /**
     * 业务方法，可多个方法重载，是否需要调用参数，默认参数……
     * @return
     */
    public String asyncRun(){
        return "定时任务：测试执行--执行一次";
    }


}
