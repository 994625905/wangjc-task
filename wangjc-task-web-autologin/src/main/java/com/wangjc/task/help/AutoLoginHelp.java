package com.wangjc.task.help;

import com.wangjc.task.base.constant.SystemConstant;
import com.wangjc.task.base.util.CookieUtil;
import com.wangjc.task.base.util.JWTUtil;
import com.wangjc.task.base.util.StringUtil;
import com.wangjc.task.service.RedisService;
import com.wangjc.task.entity.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点登录的协助
 * @author com.wangjc
 * @title: AutoLoginHelp
 * @projectName wangjc.task
 * @description: TODO
 * @date 2020/6/1718:24
 */
public class AutoLoginHelp {

    private static final Logger logger = LoggerFactory.getLogger(AutoLoginHelp.class);

    /**
     * 登录检查
     * @param request
     * @param request
     * @return
     */
    public static UserModel loginCheck(HttpServletRequest request, RedisService redisService){

        String token = CookieUtil.getCookieValue(request,"token");

        if(StringUtil.isNullOrEmpty(token)){
            return null;
        }

        UserModel user= (UserModel) redisService.hget(SystemConstant.REDIS_KEY.LOGIN, token);
        if(user != null){
            redisService.hset(SystemConstant.REDIS_KEY.LOGIN,token,user,SystemConstant.REDIS_TIME.LOGIN);//刷新redis
            return user;
        }

        logger.info("redis已过期，需重新登录。");
        return null;
    }

    /**
     * 登录,同时设置主题(后期新增多主题功能)
     * @param userModel
     * @param redisService
     * @return token
     */
    public static String login(UserModel userModel, RedisService redisService, HttpServletResponse response){

        if(userModel != null){
            String token = JWTUtil.sign(userModel.getId(),userModel.getAccount());
            // 设置token到cookie,user到redis
            CookieUtil.set(response,"token",token);
            redisService.hset(SystemConstant.REDIS_KEY.LOGIN,token,userModel,SystemConstant.REDIS_TIME.LOGIN);
            return token;
        }
        return null;
    }

    /**
     * 注销,注销user，token可能在请求头，也有可能设置参数传递
     * @param request
     * @param redisService
     */
    public static void logout(HttpServletRequest request,RedisService redisService){

        String token = StringUtil.isNullOrEmpty(request.getParameter("token"))?request.getHeader("Authorization"):request.getParameter("token");
        Long deleteHashKey = redisService.hashDeleteHashKey(SystemConstant.REDIS_KEY.LOGIN, token);
        logger.info("注销成功！删除redis存储条目[{}]", deleteHashKey);
    }



}
