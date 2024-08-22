package com.example.amqp.tutorials.rabbitmq_amqp_tutorials.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Tut1Receiver {
    private static  final Logger log = LoggerFactory.getLogger(Tut1Receiver.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message) {
        log.info(String.format("Received message: %s",message));
    }
}
