package com.stf.controller;

import com.stf.entity.Article;
import com.stf.entity.Topic;
import com.stf.service.ArticleService;
import com.stf.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    ArticleService articleService;

    @PostMapping("/topics")
    public ResponseEntity<?> createTopic(Topic topic){
        Topic topic1 = topicService.createTopic(topic);
        if (topic1 != null ){
            return new ResponseEntity<>(topic1,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Can not create topic, please check the requirements",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable Long id){
        Topic topic = topicService.findTopicById(id);
        if (topic == null){
            return new ResponseEntity<>("Topic with id " + id + " dose not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(topic,HttpStatus.OK);
    }

    @PutMapping("/topics")
    public ResponseEntity<?> updateTopic(Topic topic){
        Topic topic1 = topicService.updateTopic(topic);
        return new ResponseEntity<>(topic1,HttpStatus.OK);
    }

    @PutMapping("/topics/includeArticle/{topicId}/{articleId}")
    public ResponseEntity<?> includeArticle(@PathVariable Long topicId,
                                            @PathVariable Long articleId){
        Topic topic = topicService.findTopicById(topicId);
        Article article = articleService.findArticleById(articleId);
        if (topic == null || article == null){
            return new ResponseEntity<>("Topic id or article id does not exist",HttpStatus.BAD_REQUEST);
        }
        Topic topic1 = topicService.includeArticle(topicId,articleId);
        return new ResponseEntity<>(topic1,HttpStatus.OK);
    }

    @PutMapping("/topics/unIncludeArticle/{topicId}/{articleId}")
    public ResponseEntity<?> unIncludeArticle(@PathVariable Long topicId,
                                            @PathVariable Long articleId){
        Topic topic = topicService.findTopicById(topicId);
        Article article = articleService.findArticleById(articleId);
        if (topic == null || article == null){
            return new ResponseEntity<>("Topic id or article id does not exist",HttpStatus.BAD_REQUEST);
        }
        Topic topic1 = topicService.unIncludeArticle(topicId,articleId);
        return new ResponseEntity<>(topic1,HttpStatus.OK);
    }

    @DeleteMapping("/topics/{id}")
    public ResponseEntity<?> removeTopicById(@PathVariable Long id){
        Topic topic = topicService.findTopicById(id);
        if (topic == null){
            return new ResponseEntity<>("Topic with id " + id +" does not exist",HttpStatus.BAD_REQUEST);
        }
        topicService.removeTopic(id);
        return new ResponseEntity<>("Topic with id " + id + " removed",HttpStatus.OK);
    }




}
