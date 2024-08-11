package com.hjyp.miaowenserver.domain.article.service;

import com.hjyp.miaowenserver.domain.article.model.ArticleBaseInfo;
import com.hjyp.miaowenserver.domain.article.model.ArticlePageInfo;
import com.hjyp.miaowenserver.domain.article.repository.ArticleRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleDomainService {

    @Resource
    private ArticleRepository articleRepository;


    public ArticlePageInfo selectArticleBaseInfoByPage(int page, int pageSize) {
        // 校验参数合法性
        if (page < 1) {
            throw new IllegalArgumentException("page must be greater than 0");
        }
        if (pageSize <= 0 || pageSize > 100) {
            throw new IllegalArgumentException("page size must be greater than 0");
        }
        ArticlePageInfo result = articleRepository.selectArticleByPage(page, pageSize);

        // 校验返回结果合法性
        if (result == null) {
            throw new RuntimeException("article page info not found");
        }

        return result;
    }


    public ArticleBaseInfo selectArticleBaseInfoById(String articleId) {
        if (articleId == null) {
            throw new IllegalArgumentException("articleId is null");
        }

        ArticleBaseInfo result = articleRepository.selectArticleById(articleId);

        if (result == null) {
            throw new RuntimeException("article info not found");
        }

        return result;
    }
}
