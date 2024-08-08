package com.hjyp.miaowenserver.infrastructure.dao;

import com.hjyp.miaowenserver.infrastructure.po.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {
    Integer insert(ArticleEntity article);
    Integer update(ArticleEntity article);
    Integer delete(String articleId);
    ArticleEntity select(String articleId);
    List<ArticleEntity> selectAll();

    List<ArticleEntity> selectByPage(int start, int limit);

    Integer selectCount();

    Integer updateBrowseCount(String articleId);

    Integer updateCommentCount(String articleId);

    Integer updateLikeCount(String articleId);

    Integer updateDislikeCount(String articleId);

    Integer updateShareCount(String articleId);

    Integer updateMarkCount(String articleId);
}
