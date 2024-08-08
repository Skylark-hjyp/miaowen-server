package com.hjyp.miaowenserver.interfaces.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticlePageVo {
//    private Integer pageNo;
    private Integer totalCount;
    private List<ArticleEntityPageVo> articleList;

    public ArticlePageVo() {

    }

    public ArticlePageVo(Integer totalCount, List<ArticleEntityPageVo> articleList) {
        this.totalCount = totalCount;
        this.articleList = articleList;
    }
}
