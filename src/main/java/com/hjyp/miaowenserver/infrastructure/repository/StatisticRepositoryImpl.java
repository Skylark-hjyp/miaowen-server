package com.hjyp.miaowenserver.infrastructure.repository;

import com.hjyp.miaowenserver.domain.statistic.model.*;
import com.hjyp.miaowenserver.domain.statistic.repository.StatisticRepository;
import com.hjyp.miaowenserver.infrastructure.mapper.*;
import com.hjyp.miaowenserver.infrastructure.po.*;
import jakarta.annotation.Resource;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("statisticRepositoryImpl")
public class StatisticRepositoryImpl implements StatisticRepository {

    @Resource
    private BrowseRecordMapper browseRecordMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private DislikeRecordMapper dislikeRecordMapper;

    @Resource
    private LikeRecordMapper likeRecordMapper;

    @Resource
    private MarkRecordMapper markRecordMapper;

    @Resource
    private ShareRecordMapper shareRecordMapper;

    @Resource
    private ArticleMapper articleMapper;


    @Override
    public ArticleStatisticInfo selectStatisticData(String articleId) {
        ArticleEntity article = articleMapper.selectStatistic(articleId);
        ArticleStatisticInfo articleStatisticInfo = new ArticleStatisticInfo();
        try {
            BeanUtils.copyProperties(articleStatisticInfo, article);
        } catch (Exception e) {
            throw new IllegalStateException("对象属性赋值时发生错误");
        }
        return articleStatisticInfo;
    }

    @Override
    public List<ArticleStatisticInfo> selectStatisticDataByBatch(List<String> articleIdList) {
        List<ArticleEntity> articles = articleMapper.selectStatisticByBatch(articleIdList);
        List<ArticleStatisticInfo> articleStatisticInfos = new ArrayList<>();
        for (ArticleEntity article : articles) {
            ArticleStatisticInfo articleStatisticInfo = new ArticleStatisticInfo();
            try {
                BeanUtils.copyProperties(articleStatisticInfo, article);
            } catch (Exception e) {
                throw new IllegalStateException("对象属性赋值时发生错误");
            }
            articleStatisticInfos.add(articleStatisticInfo);
        }

        return articleStatisticInfos;
    }

    @Override
    public boolean likeArticle(LikeArticleDO likeArticleDO) {
        Integer result;

        // 更新文章数据
        if (likeArticleDO.isLike()) {
            result = articleMapper.updateStatistic(likeArticleDO.getArticleId(), "article_like_count", "+ 1");
        } else {
            result = articleMapper.updateStatistic(likeArticleDO.getArticleId(), "article_like_count", "- 1");
        }

        if (result != 1) {
            return false;
        }


        // 写入点赞记录
        LikeRecordEntity likeRecordEntity = new LikeRecordEntity();
        likeRecordEntity.setArticleId(likeArticleDO.getArticleId());
        likeRecordEntity.setUserId(likeArticleDO.getUserId());
        likeRecordEntity.setCreateTime(likeArticleDO.getCreateTime());

        // 如果是新增点赞记录
        if (likeArticleDO.isLike()) {
            likeRecordEntity.setDeleted(false);
            result = likeRecordMapper.insertLikeRecord(likeRecordEntity);
        } else {
            result = likeRecordMapper.deleteLikeRecord(likeRecordEntity);
        }

        return result > 0;
    }

    @Override
    public boolean browseArticle(BrowseArticleDO browseArticleDO) {
        Integer result;
        
        // 更新数据
        result = articleMapper.updateStatistic(browseArticleDO.getArticleId(), "article_browse_count", "+ 1");
        if (result != 1) {
            return false;
        }

        // 如果用户未登录，前端传过来的用户ID为null，此时只需要更新文章浏览量即可
        if (browseArticleDO.getUserId() == null) {
            return true;
        }
        
        // 写入浏览记录
        BrowseRecordEntity browseRecordEntity = new BrowseRecordEntity();
        browseRecordEntity.setArticleId(browseArticleDO.getArticleId());
        browseRecordEntity.setUserId(browseArticleDO.getUserId());
        browseRecordEntity.setCreateTime(browseArticleDO.getCreateTime());
        browseRecordEntity.setDeleted(false);
        result = browseRecordMapper.insertBrowseRecord(browseRecordEntity);
        return result > 0;
    }

    @Override
    public boolean dislikeArticle(DislikeArticleDO dislikeArticleDO) {
        Integer result;

        // 更新文章数据
        if (dislikeArticleDO.isDislike()) {
            result = articleMapper.updateStatistic(dislikeArticleDO.getArticleId(), "article_dislike_count", "+ 1");
        } else {
            result = articleMapper.updateStatistic(dislikeArticleDO.getArticleId(), "article_dislike_count", "- 1");
        }

        if (result != 1) {
            return false;
        }

        DislikeRecordEntity dislikeRecordEntity = new DislikeRecordEntity();
        dislikeRecordEntity.setArticleId(dislikeArticleDO.getArticleId());
        dislikeRecordEntity.setUserId(dislikeArticleDO.getUserId());
        dislikeRecordEntity.setCreateTime(dislikeArticleDO.getCreateTime());

        // 如果是新增点踩记录
        if (dislikeArticleDO.isDislike()) {
            dislikeRecordEntity.setDeleted(false);
            result = dislikeRecordMapper.insertDislikeRecord(dislikeRecordEntity);
        } else {
            result = dislikeRecordMapper.deleteDislikeRecord(dislikeRecordEntity);
        }

        return result > 0;
    }

    @Override
    public boolean markArticle(MarkArticleDO markArticleDO) {
        Integer result;

        // 更新文章数据
        if (markArticleDO.isMark()) {
            result = articleMapper.updateStatistic(markArticleDO.getArticleId(), "article_mark_count", "+ 1");
        } else {
            result = articleMapper.updateStatistic(markArticleDO.getArticleId(), "article_mark_count", "- 1");
        }

        if (result != 1) {
            return false;
        }
        
        MarkRecordEntity markRecordEntity = new MarkRecordEntity();
        markRecordEntity.setArticleId(markArticleDO.getArticleId());
        markRecordEntity.setUserId(markArticleDO.getUserId());
        markRecordEntity.setCreateTime(markArticleDO.getCreateTime());

        // 如果是新增点踩记录
        if (markArticleDO.isMark()) {
            markRecordEntity.setDeleted(false);
            result = markRecordMapper.insertMarkRecord(markRecordEntity);
        } else {
            result = markRecordMapper.deleteMarkRecord(markRecordEntity);
        }

        return result > 0;
    }

    @Override
    public boolean shareArticle(ShareArticleDO shareArticleDO) {
        Integer result;

        result = articleMapper.updateStatistic(shareArticleDO.getArticleId(), "article_share_count", "+ 1");
        if (result != 1) {
            return false;
        }

        // 写入分享表
        ShareRecordEntity shareRecordEntity = new ShareRecordEntity();
        shareRecordEntity.setArticleId(shareArticleDO.getArticleId());
        shareRecordEntity.setUserId(shareArticleDO.getUserId());
        shareRecordEntity.setCreateTime(shareArticleDO.getCreateTime());
        shareRecordEntity.setDeleted(false);
        result = shareRecordMapper.insertShareRecord(shareRecordEntity);

        return result > 0;
    }

    @Override
    public boolean commentArticle(CommentArticleDO commentArticleDO) {
        int result;

        // 更新文章数据
        if (commentArticleDO.getId() == null) {
            result = articleMapper.updateStatistic(commentArticleDO.getArticleId(), "article_comment_count", "+ 1");
        } else {
            result = articleMapper.updateStatistic(commentArticleDO.getArticleId(), "article_comment_count", "- 1");
        }

        if (result != 1) {
            return false;
        }

        // 写入评论
        CommentEntity commentEntity = getCommentEntity(commentArticleDO);

        if (commentArticleDO.getId() == null) {
            commentEntity.setDeleted(false);
            result = commentMapper.insertComment(commentEntity);
        } else {
            result = commentMapper.deleteComment(commentArticleDO.getId());
        }

        return result > 0;
    }

    private static CommentEntity getCommentEntity(CommentArticleDO commentArticleDO) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setArticleId(commentArticleDO.getArticleId());
        commentEntity.setUserId(commentArticleDO.getUserId());
        commentEntity.setCreateTime(commentArticleDO.getCreateTime());
        commentEntity.setCommentContent(commentArticleDO.getCommentContent());
        commentEntity.setParentCommentId(commentArticleDO.getParentCommentId());
        commentEntity.setReplyUserId(commentArticleDO.getReplyUserId());
        return commentEntity;
    }
}
