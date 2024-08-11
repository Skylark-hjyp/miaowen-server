package com.hjyp.miaowenserver.interfaces.dto;

import lombok.Data;

@Data
public class CommentDto extends ActionBaseDto {
    // 评论ID
    private Integer id;

    // 父评论ID，null 代表父级评论
    private Integer parentCommentId;

    // 评论内容
    private String commentContent;

    // 子评论分为两种，一种是回复父评论，此时回复用户为null；一种是回复其他子评论，此时回复用户ID不为空.
    private String replyUserId;
}
