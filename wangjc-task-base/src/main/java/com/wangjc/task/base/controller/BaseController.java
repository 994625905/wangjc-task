package com.wangjc.task.base.controller;

import org.springframework.web.servlet.ModelAndView;

/**
 * 基础controller
 * @author com.wangjc
 * @title: BaseController
 * @projectName wangjc-blog
 * @description: TODO
 * @date 2019/9/1118:14
 */
public class BaseController {

    /**
     * 视图解析
     * @param viewName
     * @return
     */
    protected ModelAndView getModelAndView(String viewName){
        return new ModelAndView(viewName);
    }
}
