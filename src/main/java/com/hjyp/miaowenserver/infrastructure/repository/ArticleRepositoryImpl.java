package com.hjyp.miaowenserver.infrastructure.repository;

import com.hjyp.miaowenserver.domain.article.model.ArticleBaseInfo;
import com.hjyp.miaowenserver.domain.article.model.ArticlePageInfo;
import com.hjyp.miaowenserver.domain.article.repository.ArticleRepository;
import com.hjyp.miaowenserver.infrastructure.mapper.ArticleMapper;
import com.hjyp.miaowenserver.infrastructure.mapper.AuthorMapper;
import com.hjyp.miaowenserver.infrastructure.po.ArticleEntity;
import com.hjyp.miaowenserver.infrastructure.po.AuthorEntity;
import com.hjyp.miaowenserver.interfaces.vo.ArticleEntityPageVo;
import com.hjyp.miaowenserver.interfaces.vo.ArticlePageVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("articleRepository")
public class ArticleRepositoryImpl implements ArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public ArticlePageInfo selectArticleByPage(int page, int pageSize) {
        // 查询文章总数
        Integer count = articleMapper.selectCount();

        if (count == null || count == 0) {
            return new ArticlePageInfo(0, null);
        }

        // 分页查询文章
        List<ArticleEntity> articleEntityList = articleMapper.selectByPage((page - 1) * pageSize, pageSize);

        // 查询文章作者ID
        List<Integer> authorIds = new ArrayList<>();
        articleEntityList.forEach((articleEntity) -> authorIds.add(articleEntity.getId()));

        // 查询文章作者，并将其转变为Map<作者ID， 头像>
        List<AuthorEntity> authorAvatars = authorMapper.selectAuthorByBatch(authorIds);
        Map<Integer, String> authorAvatarMap = new HashMap<>();
        authorAvatars.forEach((authorEntity) -> authorAvatarMap.put(authorEntity.getId(), authorEntity.getAuthorAvatar()));

        // 组合VO对象
        List<ArticleBaseInfo> articleBaseInfoList = new ArrayList<>();
        try {
            for (ArticleEntity articleEntity : articleEntityList ) {
                ArticleBaseInfo articleBaseInfo = new ArticleBaseInfo();
                BeanUtils.copyProperties(articleBaseInfo, articleEntity);
                articleBaseInfo.setAuthorAvatar(authorAvatarMap.get(articleBaseInfo.getArticleAuthorId()));
                articleBaseInfoList.add(articleBaseInfo);
            }
        } catch (Exception e) {
            throw new RuntimeException("分页查询文章对象列表赋值失败");
        }

        return new ArticlePageInfo(count, articleBaseInfoList);
    }

    @Override
    public ArticleBaseInfo selectArticleById(String articleId) {
        ArticleEntity article = articleMapper.select(articleId);

        AuthorEntity author = authorMapper.selectById(article.getArticleAuthorId());

        ArticleBaseInfo articleBaseInfo = new ArticleBaseInfo();
        try {
            BeanUtils.copyProperties(articleBaseInfo, article);
        } catch (Exception e) {
            throw new RuntimeException("查询文章对象赋值失败");
        }

        articleBaseInfo.setAuthorAvatar(author.getAuthorAvatar());

        return articleBaseInfo;
    }
}
