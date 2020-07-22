# wangjc-task
零散定时任务的规范处理，手动控制业务代码的执行
# wangjc-task.或许能帮你规范可控的管理应用定时任务
- （真实场景下的应用接入，可以更加精细化的设计代码，这只是个模型实例）

------------
- 我们在很多地方都需要用到各种各样的定时任务来批量处理数据，但是大多数采用@Scheduled注解的方式来设置的，在应用启动后，无法进行操作，这种操作包括手动的调用或者手动终止/激活
> （例如，同步远程接口数据，某次同步失败，需要复盘，那么只能手动调改代码来进行，诸如此来的局限性还有很多）
- 在github上也有很多成熟完整的案例来专门管理定时任务，我大约看了一下，特别棒。但架构体系有点庞大，如果任务量在10个以内的话，强行引入会显得很冗余，而且也没必要浪费资源，为此，咱们避重就轻，可以手动写一套简单的架构模型，可直接接入。

## 1.架构的设计
| 模块名称  | 作用  |
| ------------ | ------------ |
| wangjc-task-base  | 基类的封装，泛型的约束，常量，异常，结果集，工具类…………  |
|  wangjc-task-core |  业务代码层，操作数据库 |
| wangjc-task-web-autologin  | web层的单点登录  |
|  wangjc-task-web-timer | web层的任务模块  |
| 备注说明  | 除timer外，很多都是从项目中复制过来的，其实timer也一样，但即使是copy，也是之前自己全部手写的  |
- （这里把它单独抽成一个应用来发布）

- ### timer的底层包分类

| 包名称  | 存放作用  |
| ------------ | ------------ |
| config  | 数据源配置，freemarket配置，web配置，线程池配置  |
| core  | 定时任务的核心业务代码，每个任务对应一个core类，统一用@Component声明  |
| filter  | 拦截器  |
| help  | 协助类，这里只放置了登录状态的判定  |
| interceptors  |  web拦截器，SQL拦截器 |
| schedule  | 定时任务的设置代码，每个定时对应一个schedule，采用@Scheduled设置的定时类名用@Async("wangjcTimerAsyncServiceExecutor")声明多线程异步；实现SchedulingConfigurer的定时则不需要，它本就是新创建一个子线程来执行任务…………下文详解  |
| web  | web层管理定时任务。msg：新增，删除，修改，终止，启动，hand：手动触发  |

## 2.定时任务的管理

- ##### 1.创建类继承ThreadPoolTaskExecutor，重写方法。用于更好的管理和监测线程情况，假如需要相关线程的信息的话，可直接在子类中操作；




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

- ##### 2.配置线程池




    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.scheduling.annotation.EnableAsync;
    import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
    
    import java.util.concurrent.Executor;
    import java.util.concurrent.ThreadPoolExecutor;
    
    /**
     * 创建线程池,设置监控,用多线程来跑定时任务，安全
     * @author wangjc
     * @title: ExecutorConfig 
     * @description: TODO
     * @date 2020/5/1617:29
     */
    @Configuration
    @EnableAsync
    public class ExecutorConfig {
    
        private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);
    
        @Value("${wangjc.task.timer.executor.core_pool_size}")
        private Integer corePoolSize;
    
        @Value("${wangjc.task.timer.executor.max_pool_size}")
        private Integer maxPoolSize;
    
        @Value("${wangjc.task.timer.executor.queue_size}")
        private Integer QueueSize;
    
        @Value("${wangjc.task.timer.executor.thread_name_prefix}")
        private String threadNamePrefix;
    
        @Bean
        public Executor wangjcTimerAsyncServiceExecutor(){
            logger.info("start asyncServiceExecutor");
    
            ThreadPoolTaskExecutor executor = new WangjcTimerThreadPoolTaskExecutor();
    
            // 配置核心线程数
            executor.setCorePoolSize(this.corePoolSize);
    
            // 配置最大线程数
            executor.setMaxPoolSize(this.maxPoolSize);
    
            // 配置队列大小
            executor.setQueueCapacity(this.QueueSize);
    
            //配置线程池中的线程名称前缀
            executor.setThreadNamePrefix(this.threadNamePrefix);
    
            // rejection-policy：当pool已经达到max size的时候，如何处理新任务
            // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    
            //执行初始化
            executor.initialize();
            return executor;
        }
    }

- #####3.为了能在web层管理任务，需要将每个任务的相关数据在创建时期录入到数据库中，所以需要在页面中提交一个新增的表单；

- #####4.在wangjc-task-web-timer模块的core包下创建对应的业务类，编写核心业务代码，标注任务唯一标识常量，用于验证任务状态和记录日志时使用；




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

- #####5.在wangjc-task-web-timer模块的schedule中创建对应的任务类。多线程异步执行，每条定时任务之间相对独立，互不干扰
#### 此处分情况处理：
> 我这里做了定时任务的两种设置模式，自己看需求取舍

- 1.用@Async注解声明类，单独开辟新的线程异步处理。采用@Scheduled(cron = "0 0/5 * * * ?")来设置定时，注入核心业务core的bean，在@Scheduled声明的方法中调用业务方法，相对简单易读。但是corn表达式不可在web层动态的变更，必须手动来更改代码层，然后重启应用；




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
    
- 2.实现SchedulingConfigurer接口，重写configureTasks（）方法，然后在里面调用addTriggerTask（）方法，分别填入任务线程和触发器，
- 这里的任务是单独开辟线程来执行，无需注解声明异步，触发器中来获取数据表中的corn，可以在页面修改变更，动态的注入定时规则。
> 但是，修改定时规则后，不会立刻触发新的定时规则，它只能在旧规则触发时，然后新的规则生效，eg:假如把凌晨1点的任务改为凌晨2点，改动后的第一次触发，只有到了凌晨1点时，才会激活新的规则




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
    

- #####6.在wangjc-task-web-timer模块的web.hand下的TaskHandController新增方法，（如果新增的任务允许被手动触发的话，不允许就不用添加），根据任务有无参数的设定来新增数据接口




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

## 3.页面部分截图
![任务管理的部分截图](http://www.wangjc.vip/group1/M00/00/00/rBAAD18YBVCAULMHAAIMbxUgxas580.png "任务管理的部分截图")
![带参手动触发](http://www.wangjc.vip/group1/M00/00/00/rBAAD18YBcyAbeuwAADoKIF8otI697.png "带参手动触发")

- 完整的项目信息请查看具体项目，烦请点个star哦！
