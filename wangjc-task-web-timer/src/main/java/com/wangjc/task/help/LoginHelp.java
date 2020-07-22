package com.wangjc.task.help;

import com.wangjc.task.base.constant.SystemConstant;
import com.wangjc.task.base.util.CookieUtil;
import com.wangjc.task.base.util.StringUtil;
import com.wangjc.task.service.RedisService;
import com.wangjc.task.entity.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录业务的协助，服务于拦截器
 * @author com.wangjc
 * @title: LoginHelp
 * @projectName wangjc-blog
 * @description: TODO
 * @date 2019/9/1215:14
 */
public class LoginHelp {

    private static final Logger logger = LoggerFactory.getLogger(LoginHelp.class);

    /**
     * 登录检查
     * @param request
     * @param redisService
     * @return
     */
    public static UserModel loginCheck(HttpServletRequest request, HttpServletResponse response, RedisService redisService){

        String token = CookieUtil.getCookieValue(request,"token");

        if(StringUtil.isNullOrEmpty(token)){
            logger.info("token已过期，需重新登录。");
            return null;
        }

        UserModel user= (UserModel) redisService.hget(SystemConstant.REDIS_KEY.LOGIN, token);
        if(user != null){
            CookieUtil.set(response,"token",token);// 刷新cookie缓存
            redisService.hset(SystemConstant.REDIS_KEY.LOGIN,token,user,SystemConstant.REDIS_TIME.LOGIN);//刷新redis
            return user;
        }
        logger.info("redis已过期，需重新登录。");
        return null;
    }

}
