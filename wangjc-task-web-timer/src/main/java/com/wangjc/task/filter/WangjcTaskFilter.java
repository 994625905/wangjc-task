package com.wangjc.task.filter;

import com.wangjc.task.base.constant.SystemConstant;
import com.wangjc.task.base.util.CookieUtil;
import com.wangjc.task.base.util.StringUtil;
import com.wangjc.task.help.LoginHelp;
import com.wangjc.task.service.RedisService;
import com.wangjc.task.entity.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * @author com.wangjc
 * @title: ReportFilter
 * @projectName i4-dp
 * @description: TODO
 * @date 2019/9/1214:39
 */
public class WangjcTaskFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(WangjcTaskFilter.class);

    /**
     * redis提供的service
     */
    private RedisService redisService;
    /**
     *认证链接
     */
    private String loginURL;
    /**
     * 重定向登录地址
     */
    private String redirectServer;

    public WangjcTaskFilter(RedisService redisService, String loginURL, String redirectServer) {
        this.redisService = redisService;
        this.loginURL = loginURL;
        this.redirectServer = redirectServer;
    }

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("过滤器初始化：[{}]",filterConfig);
    }

    /**
     * 核心过滤，资源放行
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        String path = req.getServletPath();//获取访问的地址

        // 初始认证登陆的放行
        String token = request.getParameter("token");
        if(!StringUtil.isNullOrEmpty(token)){

            // 设置token到cookie
            CookieUtil.set(res,"token",token);

            UserModel userModel = (UserModel) redisService.hget(SystemConstant.REDIS_KEY.LOGIN,token);
            if(userModel == null){
                res.sendRedirect(loginURL+"?redirect="+redirectServer);//定向到登录
                return;
            }

            // 重定向，隐藏token
            if("/".equals(path)){
                res.sendRedirect(redirectServer);
                return;
            }

            chain.doFilter(request,response);
            return;
        }

        // 静态资源放行
        if(staticResource(path)){
            chain.doFilter(request,response);
            return;
        }
        //注销登录动作放行
        if(path.indexOf("/logout") != -1){
            chain.doFilter(request,response);
            logger.info("注销请求，过滤器放行 请求链接：[{}]",path);
            return;
        }
        //登录动作放行
        if(path.indexOf("/login") != -1){
            chain.doFilter(request,response);
            logger.info("登录动作，过滤器放行");
            return;
        }

        UserModel userModel = LoginHelp.loginCheck(req,res,redisService);
        if(userModel == null){
            res.sendRedirect(loginURL+"?redirect="+redirectServer);//定向到登录
            return;
        }
        chain.doFilter(request,response);
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {

    }

    /**
     * 静态资源判断放行,正常请求链接不可用"."，切记
     * @param path
     * @return
     */
    private boolean staticResource(String path){
        boolean res = path.contains(".");
        return res;
    }

}
