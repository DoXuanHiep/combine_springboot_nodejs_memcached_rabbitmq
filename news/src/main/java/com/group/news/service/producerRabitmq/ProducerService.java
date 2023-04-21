package com.group.news.service.producerRabitmq;

import com.group.news.entity.Post;

public interface ProducerService {
    void sendMessage(String message, String action);
}
