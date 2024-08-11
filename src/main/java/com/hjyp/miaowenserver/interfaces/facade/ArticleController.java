package com.hjyp.miaowenserver.interfaces.facade;

import com.hjyp.miaowenserver.application.service.ArticleService;
import com.hjyp.miaowenserver.interfaces.dto.ActionBaseDto;
import com.hjyp.miaowenserver.interfaces.dto.CommentDto;
import com.hjyp.miaowenserver.interfaces.vo.ArticleEntityPageVo;
import com.hjyp.miaowenserver.interfaces.vo.ArticlePageVo;
import com.hjyp.miaowenserver.interfaces.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/selectByPage")
    public Result<ArticlePageVo> selectArticleByPage(int page, int pageSize) {
        return articleService.selectArticleByPage(page, pageSize);
    }

    @RequestMapping("/select")
    public Result<ArticleEntityPageVo> selectArticleById(@RequestBody ActionBaseDto actionBaseDto) {
        return articleService.selectArticleById(actionBaseDto);
    }

    @RequestMapping("/like")
    public Result<Boolean> likeArticle(@RequestBody ActionBaseDto actionBaseDto) {
        return articleService.likeArticle(actionBaseDto);
    }

    @RequestMapping("/dislike")
    public Result<Boolean> dislikeArticle(@RequestBody ActionBaseDto actionBaseDto) {
        return articleService.dislikeArticle(actionBaseDto);
    }

    @RequestMapping("/share")
    public Result<Boolean> shareArticle(@RequestBody ActionBaseDto actionBaseDto) {
        return articleService.shareArticle(actionBaseDto);
    }

    @RequestMapping("/mark")
    public Result<Boolean> markArticle(@RequestBody ActionBaseDto actionBaseDto) {
        return articleService.markArticle(actionBaseDto);
    }

    @RequestMapping("/comment")
    public Result<Boolean> commentArticle(@RequestBody CommentDto commentDto) {
        return articleService.commentArticle(commentDto);
    }

}
