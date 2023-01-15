package com.stf.service;

import com.stf.entity.Article;
import com.stf.entity.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    @Override
    public List<Article> findAllArticle() {
        return articleRepository.findAll();
    }

    @Transactional
    @Override
    public Article findArticleById(Long id) {
        return articleRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    @Override
    public void removeArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Article findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}
