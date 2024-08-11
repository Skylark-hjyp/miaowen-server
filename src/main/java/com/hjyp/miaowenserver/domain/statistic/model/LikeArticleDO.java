package com.hjyp.miaowenserver.domain.statistic.model;

import lombok.Data;

import java.util.Date;

@Data
public class LikeArticleDO {

    // 用户ID
    private String userId;

    // 文章ID
    private String articleId;

    // 当前是否是点赞操作
    private boolean like;

    // 操作时间
    private Date createTime;
}
