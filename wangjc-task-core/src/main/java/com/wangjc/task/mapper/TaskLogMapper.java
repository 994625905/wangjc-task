package com.wangjc.task.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.wangjc.task.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangjc.task.entity.dto.TaskLogDto;
import com.wangjc.task.entity.model.TaskLogModel;
import com.wangjc.task.entity.view.TaskLogView;

/**
* Mapper
* @author wangjc
* @date 2020-07-21 14:28:11
*/
public interface TaskLogMapper extends BaseIMapper<TaskLogModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<TaskLogView> selectPage(TaskLogDto dto);

}
