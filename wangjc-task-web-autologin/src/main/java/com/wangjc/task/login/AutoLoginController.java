package com.wangjc.task.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wangjc.task.base.controller.BaseController;
import com.wangjc.task.base.result.ActionResult;
import com.wangjc.task.base.result.ResultEnum;
import com.wangjc.task.base.util.MD5Util;
import com.wangjc.task.help.AutoLoginHelp;
import com.wangjc.task.service.RedisService;
import com.wangjc.task.entity.dto.UserDto;
import com.wangjc.task.entity.model.UserModel;
import com.wangjc.task.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点登录的控制层
 * @author com.wangjc
 * @title: AutoLoginController
 * @projectName wangjc.task
 * @description: TODO
 * @date 2020/6/1718:22
 */
@Controller
@RequestMapping("/autoLogin")
public class AutoLoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AutoLoginController.class);

    @Autowired
    private IUserService iUserService;
    @Autowired
    private RedisService redisService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(String redirect, HttpServletRequest request){
        UserModel userModel = AutoLoginHelp.loginCheck(request, redisService);

        if(userModel == null){
            ModelAndView view = getModelAndView("/page/login");
            view.addObject("redirect", redirect);

            logger.info("登录请求，站点[{}]",redirect);
            return view;
        }
        if(redirect == null){
            return getModelAndView("/index");
        }
        return getModelAndView("redirect:"+redirect);
    }

    /**
     * 账号密码登录动作(以此类推，可添加手机验证码，微信……第三方登录接口)
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<String> login(UserDto dto, HttpServletRequest request, HttpServletResponse response){

        // 验证登录是否通过
        UserModel userModel = iUserService.getOne(new QueryWrapper<UserModel>(){{
            eq("account",dto.getUserAccount());
            eq("password",dto.getPassword());
        }});

        if(userModel != null) {
            // 设置登录
            String token = AutoLoginHelp.login(userModel, redisService,response);
            // 重定向
            String redirectUrl = request.getParameter("redirect");
            if (redirectUrl != null && redirectUrl.trim().length() > 0) {
                redirectUrl = redirectUrl + "?token=" + token;
                logger.info("登录成功，重定向地址[{}]",redirectUrl);
            }
            return ActionResult.ok(redirectUrl);
        }
        return ActionResult.error(ResultEnum.LOGIN_FAIL.getMsgCn());
    }

    /**
     * 切换用户，注销登录,销毁缓存，跳转登录页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeLogin",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView change(HttpServletRequest request){
        AutoLoginHelp.logout(request,redisService);
        return getModelAndView("/autoLogin/index");
    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView logout(HttpServletRequest request){
        AutoLoginHelp.logout(request,redisService);
        return getModelAndView("/autoLogin/index");
    }


}
