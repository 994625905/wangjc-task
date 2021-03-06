package com.wangjc.task.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPoolTaskExecutor子类，用于更好的管理和监测线程
 * @author wangjc
 * @title: I4TimerThreadPoolTaskExecutor
 * @projectName i4-dp
 * @description: TODO
 * @date 2020/5/1617:36
 */
public class WangjcTimerThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private final static Logger logger = LoggerFactory.getLogger(WangjcTimerThreadPoolTaskExecutor.class);

    /**
     * 专门用于记录线程信息
     * @param prefix
     */
    private void showThreadPoolInfo(String prefix){
        ThreadPoolExecutor thread = getThreadPoolExecutor();
        if(thread == null){
            return;
        }
        logger.info("{},{},taskCount[{}],completedTaskCount[{}],activeCount[{}],queueSize[{}]",
                this.getThreadNamePrefix(),
                prefix,
                thread.getTaskCount(),
                thread.getCompletedTaskCount(),
                thread.getActiveCount(),
                thread.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("1. do execute");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("2. do execute");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("1. do submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("2. do submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("1. do submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("2. do submitListenable");
        return super.submitListenable(task);
    }

}
