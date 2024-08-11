package com.hjyp.miaowenserver.domain.statistic.repository;

import com.hjyp.miaowenserver.domain.statistic.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticRepository {

    /**
     * 获取统计数据，包括文章的浏览量、点赞量、点踩量、收藏量、分享量、评论量
     * @param articleId
     */
    ArticleStatisticInfo selectStatisticData(String articleId);

    /**
     * 批量获取统计数据，包括文章的浏览量、点赞量、点踩量、收藏量、分享量、评论量
     * @param articleIdList
     */
    List<ArticleStatisticInfo> selectStatisticDataByBatch(List<String> articleIdList);

    /**
     * 对文章点赞或取消点赞
     * @param likeArticleDO
     * @return
     */
    boolean likeArticle(LikeArticleDO likeArticleDO);

    /**
     * 浏览文章
     * @param browseArticleDO
     * @return
     */
    boolean browseArticle(BrowseArticleDO browseArticleDO);

    /**
     * 点踩文章
     * @param dislikeArticleDO
     * @return
     */
    boolean dislikeArticle(DislikeArticleDO dislikeArticleDO);

    /**
     * 收藏或取消收藏
     * @param markArticleDO
     * @return
     */
    boolean markArticle(MarkArticleDO markArticleDO);

    /**
     * 分享文章
     * @param shareArticleDO
     * @return
     */
    boolean shareArticle(ShareArticleDO shareArticleDO);

    /**
     * 评论文章
     * @param commentArticleDO
     * @return
     */
    boolean commentArticle(CommentArticleDO commentArticleDO);
}
