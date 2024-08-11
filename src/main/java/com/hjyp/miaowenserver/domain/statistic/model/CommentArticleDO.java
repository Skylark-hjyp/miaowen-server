package com.hjyp.miaowenserver.domain.statistic.model;

import lombok.Data;

import java.util.Date;

@Data
public class CommentArticleDO {

    // 评论ID
    private Integer id;

    // 用户ID
    private String userId;

    // 文章ID
    private String articleId;

    // 父评论ID，null 代表父级评论
    private Integer parentCommentId;

    // 评论内容
    private String commentContent;

    // 子评论分为两种，一种是回复父评论，此时回复用户为null；一种是回复其他子评论，此时回复用户ID不为空.
    private String replyUserId;

    // 操作时间
    private Date createTime;
}
