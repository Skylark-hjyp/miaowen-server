package com.hjyp.miaowenserver.interfaces.facade;

import com.hjyp.miaowenserver.application.service.ArticleService;
import com.hjyp.miaowenserver.interfaces.vo.ArticleEntityPageVo;
import com.hjyp.miaowenserver.interfaces.vo.ArticlePageVo;
import com.hjyp.miaowenserver.interfaces.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @RequestMapping("/selectById")
    public Result<ArticleEntityPageVo> selectArticleById(String articleId) {
        return articleService.selectArticleById(articleId);
    }

    @RequestMapping("/hello")
    public String sayHello(String articleId) {
        return "hello";
    }

}
