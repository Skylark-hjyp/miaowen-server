package com.hjyp.miaowenserver.infrastructure.mapper;

import com.hjyp.miaowenserver.infrastructure.po.LikeRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeRecordMapper {
    /**
     * 新增点赞记录
     * @param likeRecordEntity
     * @return
     */
    Integer insertLikeRecord(LikeRecordEntity likeRecordEntity);

    /**
     * 删除点赞记录
     * @param id 点赞记录ID
     * @return
     */
    Integer deleteLikeRecord(LikeRecordEntity likeRecordEntity);
}
