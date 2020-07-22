package com.wangjc.task.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务：测试实现SchedulingConfigurer来动态注入规则
 * @author wangjc
 * @title: TestStart
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2116:59
 */
@Component
public class TestByImplCore {

    private static final Logger logger = LoggerFactory.getLogger(TestByImplCore.class);
    /** 任务的唯一标识 */
    public final static String TASK_SSID = "3df834c5-13d6-415b-a149-ac6b56f40f41";

    /**
     * 业务方法
     * @return
     */
    public String asyncRun(){
        return "定时任务：测试实现SchedulingConfigurer来动态注入规则";
    }

}
