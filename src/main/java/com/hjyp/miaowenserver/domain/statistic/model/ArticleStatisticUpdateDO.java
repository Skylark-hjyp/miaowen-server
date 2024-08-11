package com.hjyp.miaowenserver.domain.statistic.model;

import lombok.Data;

@Data
public class ArticleStatisticUpdateDO {

    /**
     * 更新操作类型 浏览量 点赞 点踩 评论 收藏 分享 等类型
     */
    private String type;

    private String startId;

    private String endId;

    /**
     * 字段更新的数量差值
     */
    private Integer count;
}
