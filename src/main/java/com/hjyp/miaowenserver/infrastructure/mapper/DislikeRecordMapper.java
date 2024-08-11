package com.hjyp.miaowenserver.infrastructure.mapper;

import com.hjyp.miaowenserver.infrastructure.po.DislikeRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DislikeRecordMapper {
    /**
     * 新增点踩记录
     * @param DislikeRecordEntity
     * @return
     */
    Integer insertDislikeRecord(DislikeRecordEntity dislikeRecordEntity);

    /**
     * 删除点踩记录
     * @param id 点踩记录ID
     * @return
     */
    Integer deleteDislikeRecord(DislikeRecordEntity dislikeRecordEntity);
}
