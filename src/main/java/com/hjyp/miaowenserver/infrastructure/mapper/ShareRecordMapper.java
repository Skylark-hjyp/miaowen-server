package com.hjyp.miaowenserver.infrastructure.mapper;

import com.hjyp.miaowenserver.infrastructure.po.ShareRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShareRecordMapper {
    /**
     * 新增分享记录
     * @param shareRecordEntity
     * @return
     */
    Integer insertShareRecord(ShareRecordEntity shareRecordEntity);
}
