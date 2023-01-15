package com.stf.controller;

import com.stf.entity.Article;
import com.stf.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/V1")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles/{id}")
    public ResponseEntity<?> findArticleById(@PathVariable Long id) {
        Article article = articleService.findArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<?> findAllArticles(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<Article> articleList = articleService.findAllArticle();
        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

    @PostMapping("/articles")
    public ResponseEntity<?> createArticle(@Valid Article article) {
        Article article1 = articleService.createArticle(article);
        return new ResponseEntity<>(article1, HttpStatus.CREATED);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<?> updateArticleById(@PathVariable Long id, Article article) {
        Article article1 = articleService.findArticleById(id);
        BeanUtils.copyProperties(article,article1);
        Article article2 = articleService.updateArticle(article1);
        return new ResponseEntity<>(article2,HttpStatus.OK);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<?> removeArticleById(@PathVariable Long id){
        Article article = articleService.findArticleById(id);
        if (article == null){
            String articleNotFound = "Article with id " + id + " not found";
            return new ResponseEntity<>(articleNotFound,HttpStatus.BAD_REQUEST);
        }
        articleService.removeArticleById(id);
        String articleRemoved = "Article with id " + id + " removed";
        return new ResponseEntity<>(articleRemoved,HttpStatus.NO_CONTENT);
    }

}
