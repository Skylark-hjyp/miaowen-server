package com.hjyp.miaowenserver.domain.statistic.model;

import lombok.Data;

import java.util.Date;

@Data
public class DislikeArticleDO {

    // 用户ID
    private String userId;

    // 文章ID
    private String articleId;

    // 当前是否是点踩操作 true为是，false为取消点踩
    private boolean dislike;

    // 操作时间
    private Date createTime;
}
