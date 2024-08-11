package com.hjyp.miaowenserver.infrastructure.mapper;

import com.hjyp.miaowenserver.infrastructure.po.BrowseRecordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrowseRecordMapper {

    /**
     * 新增浏览记录
     * @param browseRecordEntity
     * @return
     */
    Integer insertBrowseRecord(BrowseRecordEntity browseRecordEntity);

}
