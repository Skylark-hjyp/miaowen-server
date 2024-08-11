package com.hjyp.miaowenserver.domain.statistic.model;

import lombok.Data;

import java.util.Date;

@Data
public class BrowseArticleDO {
    // 用户ID
    private String userId;

    // 文章ID
    private String articleId;

    // 操作时间
    private Date createTime;
}
