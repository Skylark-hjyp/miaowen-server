package com.hjyp.miaowenserver.infrastructure.po;

import lombok.Data;

import java.util.Date;

@Data
public class DislikeRecordEntity {

    // 点踩记录ID
    private Integer id;

    // 用户ID
    private String userId;

    // 文章ID
    private String articleId;

    // 是否删除
    private Boolean deleted;

    // 操作时间
    private Date createTime;
}
