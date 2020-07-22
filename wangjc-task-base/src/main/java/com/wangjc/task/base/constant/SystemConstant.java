package com.wangjc.task.base.constant;

/**
 * 系统的常量类
 * @author com.wangjc
 * @title: SystemConstant
 * @projectName wangjc-task
 * @description: TODO
 * @date 2019/9/1614:11
 */
public class SystemConstant {

    /**
     * redis表存储的key
     */
    public interface REDIS_KEY{
        String LOGIN = "wangjc-task-login";
        String LOGIN_LOG = "wangjc-task-login-log";
        String MENU = "wangjc-task-login-menu";
        String SYSTEM_CONSTANT = "wangjc-task-system-constant";//更新时主动刷新redis
        String IMAGE_SELECT_TEMP = "wangjc-task-image-select-temp";//图片选中的临时值
        String ESSAY = "wangjc-task-essay";//文章
        String PICTURE = "wangjc-task-picture";//图片
        String NOTE = "wangjc-task-note";//笔记
        String PROJECT = "wangjc-task-project";//实例
    }

    /**
     * redis表存储的过期时间
     */
    public interface REDIS_TIME{
        Long LOGIN = 60 * 60 * 24 * 7L;//7天
        Long LOGIN_LOG = 60 * 60 * 24 * 7L;//7天
        Long MENU = 60 * 60 * 24 * 1L;//1天
        Long SYSTEM_CONSTANT = 60 * 60 * 24 * 365L;//1年，永不失效
        Long IMAGE_SELECT_TEMP = 60 * 60 * 24 * 1L;//1天
        Long ESSAY = 60 * 60 * 24 * 1L;//1天
        Long PICTURE = 60 * 60 * 24 * 1L;//1天
        Long NOTE = 60 * 60 * 24 * 1L;//1天
        Long PROJECT = 60 * 60 * 24 * 1L;//1天
    }

    /** 定时任务的常量 */
    public interface SCHEDULE_TASK{

        /** 状态 */
        interface STATUS{
            Integer RUN = 1;//正常运行
            Integer STOP = 0;//停机
        }
        /** 允许外部手动触发 */
        interface ALLOW_DO{
            Integer YES = 1;//允许
            Integer NO = 0;//不允许
        }
        /** 运行方式 */
        interface RUN_TYPE{
            Integer AUTO = 0;//自动
            Integer HAND = 1;//手动
        }
        /** 执行结果 */
        interface RUN_RESULT{
            Integer FAIL = 0;//失败
            Integer SUCCESS = 1;//成功
        }
    }


}
