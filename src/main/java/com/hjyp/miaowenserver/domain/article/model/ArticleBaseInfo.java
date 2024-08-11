package com.hjyp.miaowenserver.domain.article.model;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleBaseInfo {
    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章作者ID
     */
    private Integer articleAuthorId;

    /**
     * 文章作者名称
     */
    private String articleAuthorName;

    /**
     * 作者头像链接
     */
    private String authorAvatar;

    /**
     * 文章链接
     */
    private String articleLink;

    /**
     * 文章发布时间
     */
    private Date articlePublishTime;

    /**
     * 文章收录时间
     */
    private Date articleReceiveTime;

    /**
     * 文章所属标签，以英文逗号分隔
     */
    private String articleTag;
}
