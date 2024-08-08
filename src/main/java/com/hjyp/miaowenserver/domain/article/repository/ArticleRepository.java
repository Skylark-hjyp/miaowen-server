package com.hjyp.miaowenserver.domain.article.repository;

import com.hjyp.miaowenserver.interfaces.vo.ArticleEntityPageVo;
import com.hjyp.miaowenserver.interfaces.vo.ArticlePageVo;

public interface ArticleRepository {
    /**
     * 分页查询文章信息
     * @param page 当前查询页数
     * @param pageSize 每页数量
     * @return 分页查询VO对象
     */
    ArticlePageVo selectArticleByPage(int page, int pageSize);

    /**
     * 查询文章详细信息
     * @param articleId 文章ID
     * @return 文章详细信息
     */
    ArticleEntityPageVo selectArticleById(String articleId);
}
