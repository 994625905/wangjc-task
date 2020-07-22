package com.wangjc.task.base.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 基础的service接口,泛型对应实体为model
 * @author com.wangjc
 * @title: BaseService
 * @projectName wangjc-blog
 * @description: TODO
 * @date 2019/9/1118:23
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 保存，只保存对象中有值 的字段
     * @param t
     * @return
     */
    boolean save(T t);

    /**
     * 修改，根据id只修改有值的字段，
     * @param t
     * @return
     */
    boolean modify(T t);

}
