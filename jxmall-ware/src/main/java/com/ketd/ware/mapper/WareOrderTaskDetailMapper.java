package com.ketd.ware.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;


import com.ketd.ware.domain.WareOrderTaskDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 库存工作单Mapper接口
 * 
 * @author ketd
 * @date 2024-04-21
 */
public interface WareOrderTaskDetailMapper extends BaseMapper<WareOrderTaskDetail>
{

    List<WareOrderTaskDetail> selectAllByTaskIdAndLockStatus(@Param("taskId") Long taskId, @Param("lockStatus") Integer lockStatus);
}
