package com.hjyp.miaowenserver.infrastructure.mapper;

import com.hjyp.miaowenserver.infrastructure.po.MarkRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarkRecordMapper {

    /**
     * 新增收藏记录
     * @param markRecordEntity
     * @return
     */
    Integer insertMarkRecord(MarkRecordEntity markRecordEntity);

    /**
     * 删除收藏记录
     * @param id 收藏记录ID
     * @return
     */
    Integer deleteMarkRecord(MarkRecordEntity markRecordEntity);
}
