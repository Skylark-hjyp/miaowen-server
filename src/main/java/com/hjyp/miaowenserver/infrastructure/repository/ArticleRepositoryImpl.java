package com.hjyp.miaowenserver.infrastructure.repository;

import com.hjyp.miaowenserver.domain.article.repository.ArticleRepository;
import com.hjyp.miaowenserver.infrastructure.dao.ArticleDao;
import com.hjyp.miaowenserver.infrastructure.dao.AuthorDao;
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
    private ArticleDao articleDao;

    @Autowired
    private AuthorDao authorDao;

    @Override
    public ArticlePageVo selectArticleByPage(int page, int pageSize) {
        // 查询文章总数
        Integer count = articleDao.selectCount();

        if (count == null || count == 0) {
            return new ArticlePageVo(0, null);
        }

        // 分页查询文章
        List<ArticleEntity> articleEntityList = articleDao.selectByPage((page - 1) * pageSize, pageSize);

        // 查询文章作者ID
        List<Integer> authorIds = new ArrayList<>();
        articleEntityList.forEach((articleEntity) -> {
            authorIds.add(articleEntity.getId());
        });

        // 查询文章作者，并将其转变为Map<作者ID， 头像>
        List<AuthorEntity> authorAvatars = authorDao.selectAuthorByBatch(authorIds);
        if (authorAvatars.isEmpty()) {
            return new ArticlePageVo(0, null);
        }

        Map<Integer, String> authorAvatarMap = new HashMap<>();
        authorAvatars.forEach((authorEntity) -> authorAvatarMap.put(authorEntity.getId(), authorEntity.getAuthorAvatar()));

        // 组合VO对象
        List<ArticleEntityPageVo> articleEntityPageVoList = new ArrayList<>();
        try {
            for (ArticleEntity articleEntity : articleEntityList ) {
                ArticleEntityPageVo articleEntityPageVo = new ArticleEntityPageVo();
                BeanUtils.copyProperties(articleEntityPageVo, articleEntity);
                articleEntityPageVo.setAuthorAvatar(authorAvatarMap.get(articleEntityPageVo.getArticleAuthorId()));
                articleEntityPageVoList.add(articleEntityPageVo);
            }
        } catch (Exception e) {
            throw new RuntimeException("分页查询文章对象列表赋值失败");
        }

        return new ArticlePageVo(count, articleEntityPageVoList);
    }

    @Override
    public ArticleEntityPageVo selectArticleById(String articleId) {
        ArticleEntity article = articleDao.select(articleId);

        AuthorEntity author = authorDao.selectById(article.getArticleAuthorId());

        ArticleEntityPageVo articleEntityPageVo = new ArticleEntityPageVo();
        try {
            BeanUtils.copyProperties(articleEntityPageVo, article);
        } catch (Exception e) {
            throw new RuntimeException("查询文章对象赋值失败");
        }

        articleEntityPageVo.setAuthorAvatar(author.getAuthorAvatar());

        return articleEntityPageVo;
    }
}
