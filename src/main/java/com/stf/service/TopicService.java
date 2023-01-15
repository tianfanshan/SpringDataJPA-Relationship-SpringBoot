package com.stf.service;

import com.stf.entity.Topic;

public interface TopicService {

    Topic createTopic(Topic topic);

    Topic findTopicById(Long id);

    Topic updateTopic(Topic topic);

    Topic includeArticle(Long topicId, Long articleId);

    Topic unIncludeArticle(Long topicId, Long articleId);

    void removeTopic(Long id);
}
