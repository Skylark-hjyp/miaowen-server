package com.hjyp.miaowenserver.application.service;

import com.hjyp.miaowenserver.interfaces.vo.ArticleEntityPageVo;
import com.hjyp.miaowenserver.interfaces.vo.ArticlePageVo;
import com.hjyp.miaowenserver.interfaces.common.Result;

public interface ArticleService {
    Result<ArticlePageVo> selectArticleByPage(int page, int pageSize);

    Result<ArticleEntityPageVo> selectArticleById(String articleId);

}
