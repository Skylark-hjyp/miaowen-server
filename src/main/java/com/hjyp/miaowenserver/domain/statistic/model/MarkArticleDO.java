package com.hjyp.miaowenserver.domain.statistic.model;


import lombok.Data;

import java.util.Date;

@Data
public class MarkArticleDO {

    // 用户ID
    private String userId;

    // 文章ID
    private String articleId;

    // 当前是否是收藏操作： true 代表收藏操作，false 代表取消收藏
    private boolean mark;

    // 操作时间
    private Date createTime;
}
