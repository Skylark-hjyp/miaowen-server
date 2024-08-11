package com.hjyp.miaowenserver.domain.statistic.model;

import lombok.Data;

@Data
public class ArticleStatisticInfo {
    /**
     * 文章 ID
     */
    private String articleId;

    /**
     * 文章浏览量
     */
    private Integer articleBrowseCount;

    /**
     * 文章评论数量
     */
    private Integer articleCommentCount;

    /**
     * 文章点赞数量
     */
    private Integer articleLikeCount;

    /**
     * 文章点踩数量
     */
    private Integer articleDislikeCount;

    /**
     * 文章收藏数量
     */
    private Integer articleMarkCount;

    /**
     * 文章分享数量
     */
    private Integer articleShareCount;
}
