package com.stf.service;

import com.stf.entity.Article;
import com.stf.entity.ArticleRepository;
import com.stf.entity.Topic;
import com.stf.entity.TopicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImp implements TopicService{

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Transactional
    @Override
    public Topic findTopicById(Long id) {
        return topicRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Topic updateTopic(Topic topic) {
        return topicRepository.save(topic);
    }


    @Transactional
    @Override
    public Topic includeArticle(Long topicId, Long articleId) {
        Topic topic = topicRepository.findById(topicId).get();
        Article article = articleRepository.findById(articleId).get();
        topic.getArticles().add(article);
        return topic;
    }

    @Transactional
    @Override
    public Topic unIncludeArticle(Long topicId, Long articleId) {
        Topic topic = topicRepository.findById(topicId).get();
        Article article = articleRepository.findById(articleId).get();
        topic.getArticles().remove(article);
        return topic;
    }

    @Transactional
    @Override
    public void removeTopic(Long id) {
        topicRepository.deleteById(id);
    }
}
