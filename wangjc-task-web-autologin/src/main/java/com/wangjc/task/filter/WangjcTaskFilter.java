package com.wangjc.task.filter;

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
 * @projectName wangjc.task
 * @description: TODO
 * @date 2019/9/1214:39
 */
public class WangjcTaskFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(WangjcTaskFilter.class);

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

        chain.doFilter(request,response);
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {

    }

}
