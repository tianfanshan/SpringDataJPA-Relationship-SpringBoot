package com.stf.service;

import com.stf.entity.Article;

import java.util.List;

public interface ArticleService {

    Article findArticleById(Long id);

    List<Article> findAllArticle();

    Article createArticle(Article article);

    Article updateArticle(Article article);

    void removeArticleById(Long id);

    Article findByTitle(String title);
}
