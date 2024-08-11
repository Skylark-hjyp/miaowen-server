package com.hjyp.miaowenserver.application.service;

import com.hjyp.miaowenserver.domain.article.model.ArticleBaseInfo;
import com.hjyp.miaowenserver.domain.article.model.ArticlePageInfo;
import com.hjyp.miaowenserver.domain.article.service.ArticleDomainService;
import com.hjyp.miaowenserver.domain.statistic.model.*;
import com.hjyp.miaowenserver.domain.statistic.service.StatisticService;
import com.hjyp.miaowenserver.interfaces.dto.ActionBaseDto;
import com.hjyp.miaowenserver.interfaces.dto.CommentDto;
import com.hjyp.miaowenserver.interfaces.vo.ArticleEntityPageVo;
import com.hjyp.miaowenserver.interfaces.vo.ArticlePageVo;
import com.hjyp.miaowenserver.interfaces.common.Result;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Resource
    private ArticleDomainService articleDomainService;

    @Resource
    private StatisticService statisticService;

    /**
     * 分页获取文章
     * @param page
     * @param pageSize
     * @return
     */
    public Result<ArticlePageVo> selectArticleByPage(int page, int pageSize) {

        // 分页获取文章基本信息
        ArticlePageInfo articlePageInfo = articleDomainService.selectArticleBaseInfoByPage(page, pageSize);

        // 批量获取文章统计信息
        List<String> articleIds = new ArrayList<>();
        articlePageInfo.getArticleList().forEach(e -> articleIds.add(e.getArticleId()));
        List<ArticleStatisticInfo> statisticInfos = statisticService.selectStatisticDataByBatch(articleIds);

        Map<String, ArticleStatisticInfo> articleStatisticInfoMap = statisticInfos.stream()
                .filter(e -> e != null && !e.getArticleId().isEmpty())
                .collect(Collectors.toMap(ArticleStatisticInfo::getArticleId, e -> e));

        // 组装对象
        ArticlePageVo articlePageVo = new ArticlePageVo();
        articlePageVo.setTotalCount(articlePageInfo.getTotalCount());
        articlePageVo.setArticleList(new ArrayList<>());
        for (ArticleBaseInfo articleBaseInfo : articlePageInfo.getArticleList()) {
            ArticleEntityPageVo articleEntityPageVo = new ArticleEntityPageVo();
            ArticleStatisticInfo articleStatisticInfo = articleStatisticInfoMap.get(articleBaseInfo.getArticleId());
            BeanUtils.copyProperties(articleBaseInfo, articleEntityPageVo);
            BeanUtils.copyProperties(articleStatisticInfo, articleEntityPageVo);
            articlePageVo.getArticleList().add(articleEntityPageVo);
        }

        return new Result<ArticlePageVo>().success(articlePageVo);
    }

    /**
     * 获取文章详情
     * @param actionBaseDto
     * @return
     */
    public Result<ArticleEntityPageVo> selectArticleById(ActionBaseDto actionBaseDto) {
        // 校验参数
        checkAction(actionBaseDto);

        // TODO：获取评论

        // 增加浏览记录
        BrowseArticleDO browseArticleDO = new BrowseArticleDO();
        browseArticleDO.setArticleId(actionBaseDto.getArticleId());
        browseArticleDO.setUserId(actionBaseDto.getUserId());
        browseArticleDO.setCreateTime(actionBaseDto.getCreateTime());
        boolean result = statisticService.browseArticle(browseArticleDO);

        // 如果写入浏览记录成功
        if (result) {
            return new Result<ArticleEntityPageVo>().success(new ArticleEntityPageVo());
        }
        return new Result<ArticleEntityPageVo>().error("00001", "查询文章详情失败");
    }

    /**
     * 点赞或取消点赞
     * @param actionBaseDto
     * @return
     */
    public Result<Boolean> likeArticle(ActionBaseDto actionBaseDto) {
        // 校验参数
        checkAction(actionBaseDto);

        // 增加点赞记录
        LikeArticleDO likeArticleDO = new LikeArticleDO();
        likeArticleDO.setArticleId(actionBaseDto.getArticleId());
        likeArticleDO.setUserId(actionBaseDto.getUserId());
        likeArticleDO.setCreateTime(actionBaseDto.getCreateTime());
        likeArticleDO.setLike(actionBaseDto.isInsert());
        boolean result = statisticService.likeArticle(likeArticleDO);

        // 如果写入浏览记录成功
        if (result) {
            return new Result<Boolean>().success(true);
        }
        return new Result<Boolean>().error("00002", "点赞失败");
    }

    /**
     * 点踩或取消点踩
     * @param actionBaseDto
     * @return
     */
    public Result<Boolean> dislikeArticle(ActionBaseDto actionBaseDto) {
        // 校验参数
        checkAction(actionBaseDto);

        // 增加点踩记录
        DislikeArticleDO dislikeArticleDO = new DislikeArticleDO();
        dislikeArticleDO.setArticleId(actionBaseDto.getArticleId());
        dislikeArticleDO.setUserId(actionBaseDto.getUserId());
        dislikeArticleDO.setCreateTime(actionBaseDto.getCreateTime());
        dislikeArticleDO.setDislike(actionBaseDto.isInsert());
        boolean result = statisticService.dislikeArticle(dislikeArticleDO);

        // 如果写入浏览记录成功
        if (result) {
            return new Result<Boolean>().success(true);
        }
        return new Result<Boolean>().error("00003", "点踩失败");
    }

    /**
     * 分享文章
     * @param actionBaseDto
     * @return
     */
    public Result<Boolean> shareArticle(ActionBaseDto actionBaseDto) {
        // 校验参数
        checkAction(actionBaseDto);

        // 增加分享记录
        ShareArticleDO shareArticleDO = new ShareArticleDO();
        shareArticleDO.setArticleId(actionBaseDto.getArticleId());
        shareArticleDO.setUserId(actionBaseDto.getUserId());
        shareArticleDO.setCreateTime(actionBaseDto.getCreateTime());
        boolean result = statisticService.shareArticle(shareArticleDO);

        // 如果写入分享记录成功
        if (result) {
            return new Result<Boolean>().success(true);
        }
        return new Result<Boolean>().error("00004", "分享失败");
    }

    /**
     * 收藏或取消收藏文章
     * @param actionBaseDto
     * @return
     */
    public Result<Boolean> markArticle(ActionBaseDto actionBaseDto) {
        // 校验参数
        checkAction(actionBaseDto);

        // 增加收藏记录
        MarkArticleDO markArticleDO = new MarkArticleDO();
        markArticleDO.setArticleId(actionBaseDto.getArticleId());
        markArticleDO.setUserId(actionBaseDto.getUserId());
        markArticleDO.setCreateTime(actionBaseDto.getCreateTime());
        markArticleDO.setMark(actionBaseDto.isInsert());
        boolean result = statisticService.markArticle(markArticleDO);

        // 如果写入收藏记录成功
        if (result) {
            return new Result<Boolean>().success(true);
        }
        return new Result<Boolean>().error("00004", "收藏失败");
    }

    /**
     * 评论文章或删除评论
     * @param commentDto
     * @return
     */
    public Result<Boolean> commentArticle(CommentDto commentDto) {
        // 校验参数
        checkAction(commentDto);

        // 增加评论记录
        CommentArticleDO commentArticleDO = new CommentArticleDO();
        BeanUtils.copyProperties(commentDto, commentArticleDO);
        boolean result = statisticService.commentArticle(commentArticleDO);

        // 如果写入评论记录成功
        if (result) {
            return new Result<Boolean>().success(true);
        }
        return new Result<Boolean>().error("00005", "评论失败");
    }

    /**
     * 检查参数合法性
     * @param actionBaseDto
     */
    private void checkAction(ActionBaseDto actionBaseDto) {
        if (actionBaseDto.getArticleId() == null || actionBaseDto.getArticleId().isEmpty()) {
            throw new IllegalArgumentException("article id is empty");
        }

        if (actionBaseDto.getUserId() == null || actionBaseDto.getUserId().isEmpty()) {
            throw new IllegalArgumentException("user id is empty");
        }
    }
}
