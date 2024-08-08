package com.hjyp.miaowenserver.domain.article.service;

import com.hjyp.miaowenserver.application.service.ArticleService;
import com.hjyp.miaowenserver.domain.article.repository.ArticleRepository;
import com.hjyp.miaowenserver.interfaces.vo.ArticleEntityPageVo;
import com.hjyp.miaowenserver.interfaces.vo.ArticlePageVo;
import com.hjyp.miaowenserver.interfaces.common.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    @Override
    public Result<ArticlePageVo> selectArticleByPage(int page, int pageSize) {
        // 校验参数合法性
        if (page < 1) {
            throw new RuntimeException("page must be greater than 0");
        }
        if (pageSize <= 0 || pageSize > 100) {
            throw new RuntimeException("page size must be greater than 0");
        }
        ArticlePageVo result = articleRepository.selectArticleByPage(page, pageSize);

        // 校验返回结果合法性
        if (result == null) {
            throw new RuntimeException("article page info not found");
        }

        Result<ArticlePageVo> returnResult = new Result<>();
        return returnResult.success(result);
    }

    @Override
    public Result<ArticleEntityPageVo> selectArticleById(String articleId) {
        if (articleId == null) {
            throw new RuntimeException("articleId is null");
        }

        ArticleEntityPageVo result = articleRepository.selectArticleById(articleId);

        if (result == null) {
            throw new RuntimeException("article info not found");
        }

        Result<ArticleEntityPageVo> returnResult = new Result<>();
        return returnResult.success(result);
    }
}
