package com.wangjc.task.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangjc.task.base.service.BaseService;

/**
 * 基础的service实现，泛型M为mapper
 * @author com.wangjc
 * @title: BaseServiceImpl
 * @projectName wangjc-blog
 * @description: TODO
 * @date 2019/9/1118:24
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> implements BaseService<T> {

    @Override
    public boolean save(T entity) {
        return super.save(entity);
    }

    @Override
    public boolean modify(T t) {
        return super.updateById(t);
    }
}
