package com.example.amqp.tutorials.rabbitmq_amqp_tutorials.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Tut1Sender {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKeyName;

    @Autowired
    RabbitTemplate template;


    private static final Logger log = LoggerFactory.getLogger(Tut1Sender.class);


    public void send(String message) {
        log.info(String.format("Sending message: %s", message));
        template.convertAndSend(exchangeName, routingKeyName, message);

    }
}
