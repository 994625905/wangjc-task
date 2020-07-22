package com.wangjc.task.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wangjc.task.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.entity.dto.TaskDto;
import com.wangjc.task.entity.model.TaskModel;
import com.wangjc.task.entity.view.TaskView;

/**
* Mapper
* @author wangjc
* @date 2020-07-21 14:28:11
*/
public interface TaskMapper extends BaseIMapper<TaskModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<TaskView> selectPage(TaskDto dto);

    /**
     * 查询所有
     * @return
     */
    List<TaskView> getListAll();
}
