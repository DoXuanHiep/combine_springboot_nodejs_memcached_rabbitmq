package com.group.news.service.producerRabitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService{
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.create}")
    private String routingKeyCreate;

    @Value("${rabbitmq.routing.key.update}")
    private String routingKeyUpdate;

    @Value("${rabbitmq.routing.key.like}")
    private String routingKeyLike;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

    private RabbitTemplate rabbitTemplate;

    public ProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message, String action) {
        LOGGER.info(String.format("Message sent -> %s", message));
        if (action == "create_post") {
            rabbitTemplate.convertAndSend(exchange, routingKeyCreate, message);
        } else if (action == "update_post") {
            rabbitTemplate.convertAndSend(exchange, routingKeyUpdate, message);
        } else if (action == "like_post") {
            rabbitTemplate.convertAndSend(exchange, routingKeyLike, message);
        } else {
            throw new RuntimeException("action's not existed");
        }
    }
}
