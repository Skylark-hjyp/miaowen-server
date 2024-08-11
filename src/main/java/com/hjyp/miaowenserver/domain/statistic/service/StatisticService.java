package com.hjyp.miaowenserver.domain.statistic.service;

import com.hjyp.miaowenserver.domain.statistic.model.*;
import com.hjyp.miaowenserver.domain.statistic.repository.StatisticRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    @Resource
    private StatisticRepository statisticRepository;

    /**
     * 获取统计数据，包括文章的浏览量、点赞量、点踩量、收藏量、分享量、评论量
     * @param articleId
     */
    public ArticleStatisticInfo selectStatisticData(String articleId) {
        if (articleId == null || articleId.trim().isEmpty()) {
            throw new IllegalArgumentException("articleId is null or empty");
        }
        return statisticRepository.selectStatisticData(articleId);
    }

    /**
     * 批量获取统计数据，包括文章的浏览量、点赞量、点踩量、收藏量、分享量、评论量
     * @param articleIdList
     */
    public List<ArticleStatisticInfo> selectStatisticDataByBatch(List<String> articleIdList) {
        if (articleIdList == null || articleIdList.isEmpty()) {
            throw new IllegalArgumentException("articleIdList is null or empty");
        }
        return statisticRepository.selectStatisticDataByBatch(articleIdList);
    }

    /**
     * 对文章点赞或取消点赞
     * @param likeArticleDO
     * @return
     */
    public boolean likeArticle(LikeArticleDO likeArticleDO) {
        if (likeArticleDO == null) {
            throw new IllegalArgumentException("likeArticleDO is null");
        }

        if (likeArticleDO.getArticleId() == null || likeArticleDO.getArticleId().trim().isEmpty()) {
            throw new IllegalArgumentException("likeArticleDO.getArticleId is null or empty");
        }

        if (likeArticleDO.getUserId() == null || likeArticleDO.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("likeArticleDO.getUserId is null or empty");
        }

        boolean result = statisticRepository.likeArticle(likeArticleDO);

        if (!result) {
            throw new IllegalArgumentException("写入点赞记录失败");
        }

        return true;
    }

    /**
     * 浏览文章
     * @param browseArticleDO
     * @return
     */
    public boolean browseArticle(BrowseArticleDO browseArticleDO) {
        if (browseArticleDO == null) {
            throw new IllegalArgumentException("browseArticleDO is null");
        }
        if (browseArticleDO.getArticleId() == null || browseArticleDO.getArticleId().trim().isEmpty()) {
            throw new IllegalArgumentException("browseArticleDO.getArticleId is null or empty");
        }
        if (browseArticleDO.getUserId() == null || browseArticleDO.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("browseArticleDO.getUserId is null or empty");
        }

        boolean result = statisticRepository.browseArticle(browseArticleDO);

        if (!result) {
            throw new IllegalArgumentException("写入浏览记录失败");
        }

        return true;
    }

    /**
     * 点踩文章
     * @param dislikeArticleDO
     * @return
     */
    public boolean dislikeArticle(DislikeArticleDO dislikeArticleDO) {

        if (dislikeArticleDO == null) {
            throw new IllegalArgumentException("dislikeArticleDO is null");
        }
        if (dislikeArticleDO.getArticleId() == null || dislikeArticleDO.getArticleId().trim().isEmpty()) {
            throw new IllegalArgumentException("dislikeArticleDO.getArticleId is null or empty");
        }
        if (dislikeArticleDO.getUserId() == null || dislikeArticleDO.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("dislikeArticleDO.getUserId is null or empty");
        }

        boolean result = statisticRepository.dislikeArticle(dislikeArticleDO);

        if (!result) {
            throw new IllegalArgumentException("写入点踩记录失败");
        }

        return true;
    }

    /**
     * 收藏或取消收藏
     * @param markArticleDO
     * @return
     */
    public boolean markArticle(MarkArticleDO markArticleDO) {

        if (markArticleDO == null) {
            throw new IllegalArgumentException("markArticleDO is null");
        }
        if (markArticleDO.getArticleId() == null || markArticleDO.getArticleId().trim().isEmpty()) {
            throw new IllegalArgumentException("markArticleDO.getArticleId is null or empty");
        }
        if (markArticleDO.getUserId() == null || markArticleDO.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("markArticleDO.getUserId is null or empty");
        }

        boolean result = statisticRepository.markArticle(markArticleDO);

        if (!result) {
            throw new IllegalArgumentException("写入收藏记录失败");
        }

        return true;
    }

    /**
     * 分享文章
     * @param shareArticleDO
     * @return
     */
    public boolean shareArticle(ShareArticleDO shareArticleDO) {
        if (shareArticleDO == null) {
            throw new IllegalArgumentException("shareArticleDO is null");
        }
        if (shareArticleDO.getArticleId() == null || shareArticleDO.getArticleId().trim().isEmpty()) {
            throw new IllegalArgumentException("shareArticleDO.getArticleId is null or empty");
        }
        if (shareArticleDO.getUserId() == null || shareArticleDO.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("shareArticleDO.getUserId is null or empty");
        }

        boolean result = statisticRepository.shareArticle(shareArticleDO);

        if (!result) {
            throw new IllegalArgumentException("写入分享记录失败");
        }

        return true;
    }

    /**
     * 评论文章
     * @param commentArticleDO
     * @return
     */
    public boolean commentArticle(CommentArticleDO commentArticleDO) {
        if (commentArticleDO == null) {
            throw new IllegalArgumentException("commentArticleDO is null");
        }
        if (commentArticleDO.getArticleId() == null || commentArticleDO.getArticleId().trim().isEmpty()) {
            throw new IllegalArgumentException("commentArticleDO.getArticleId is null or empty");
        }
        if (commentArticleDO.getUserId() == null || commentArticleDO.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("commentArticleDO.getUserId is null or empty");
        }

        boolean result = statisticRepository.commentArticle(commentArticleDO);

        if (!result) {
            throw new IllegalArgumentException("写入评论失败");
        }

        return true;
    }

}
