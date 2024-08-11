package com.hjyp.miaowenserver.domain.article.model;

import lombok.Data;

import java.util.List;

@Data
public class ArticlePageInfo {
    private Integer totalCount;
    private List<ArticleBaseInfo> articleList;

    public ArticlePageInfo(Integer totalCount, List<ArticleBaseInfo> articleList) {
        this.totalCount = totalCount;
        this.articleList = articleList;
    }

    public ArticlePageInfo() {

    }
}
