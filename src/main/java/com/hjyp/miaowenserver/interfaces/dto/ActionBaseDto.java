package com.hjyp.miaowenserver.interfaces.dto;

import lombok.Data;

import java.util.Date;

/**
 * 动作基础Dto类，可用于点赞、点踩、收藏、分享、查询文章详细信息
 */
@Data
public class ActionBaseDto {
    // 用户ID
    private String userId;

    // 文章ID
    private String articleId;

    // 当前是否是新增操作
    private boolean insert;

    // 操作时间
    private Date createTime;
}
