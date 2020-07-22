package com.wangjc.task.core;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 定时任务：测试终止的业务编写
 * @author wangjc
 * @title: TestStart
 * @projectName wangjc-task
 * @description: TODO
 * @date 2020/7/2116:59
 */
@Component
public class TestStopCore {

    private static final Logger logger = LoggerFactory.getLogger(TestStopCore.class);
    /** 任务的唯一标识 */
    public final static String TASK_SSID = "f2f8ce21-bf93-4178-8abe-c94f14d8fcd1";

    /**
     * 带参任务
     * @param map
     * @return
     */
    public String asyncRun(Map<String,Object> map){
        logger.info("任务带参，[{}]", JSON.toJSONString(map));
        return "定时任务：测试终止--执行一次";
    }

    /**
     * 业务方法
     * @return
     */
    public String asyncRun(){
        return "定时任务：测试终止--执行一次";
    }


}
