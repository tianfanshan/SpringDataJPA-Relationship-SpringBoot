package com.stf.controller;

import com.stf.entity.Article;
import com.stf.entity.Comment;
import com.stf.service.ArticleService;
import com.stf.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/V1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/comments/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id){
        Comment comment = commentService.getCommentById(id);
        if (comment == null){
            return new ResponseEntity<>("Comment with id " + id + " not found",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @GetMapping("/comments/{title}")
    public ResponseEntity<?> getCommentsByArticle(@PathVariable String title){
        Article article = articleService.findByTitle(title);
        List<Comment> comments = commentService.findAllCommentsByArticle(article);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<?> updateCommentById(@PathVariable Long id,Comment comment){
        Comment comment1 = commentService.getCommentById(id);
        if (comment1 == null){
            return new ResponseEntity<>("Comment with id " + id + " not found",HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(comment,comment1);
        Comment comment2 = commentService.updateComment(comment1);
        return new ResponseEntity<>(comment2,HttpStatus.OK);
    }

    @PostMapping("/comments/{articleId}")
    public ResponseEntity<?> createComment(@PathVariable Long articleId,Comment comment){
        Article article = articleService.findArticleById(articleId);
        if (article == null){
            return new ResponseEntity<>("You can't comment a unexist article",HttpStatus.BAD_REQUEST);
        }
        Comment comment1 = commentService.createComment(comment);
        return new ResponseEntity<>(comment1,HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> removeCommentById(@PathVariable Long id){
        Comment comment = commentService.getCommentById(id);
        if (comment == null){
            return new ResponseEntity<>("The comment you want to remove dose not exist",HttpStatus.BAD_REQUEST);
        }
        commentService.removeCommentById(id);
        return new ResponseEntity<>("Comment with id " + id + " removed", HttpStatus.NO_CONTENT);
    }

}
