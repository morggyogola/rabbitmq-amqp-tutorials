package com.example.amqp.tutorials.rabbitmq_amqp_tutorials.publisher;

import com.example.amqp.tutorials.rabbitmq_amqp_tutorials.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Tut1Sender {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKeyName;

    @Value("${rabbitmq.routing.key.json.name}")
    private String jsonRoutingKeyName;

    @Autowired
    RabbitTemplate template;

    @Autowired
    AmqpTemplate amqpTemplate;


    private static final Logger log = LoggerFactory.getLogger(Tut1Sender.class);


    public void send(String message) {
        log.info(String.format("Sending message: %s", message));
        template.convertAndSend(exchangeName, routingKeyName, message);

    }
    public void sendJson(UserDto userDto) {
        log.info(String.format("Sending message: %s", userDto.toString()));
        template.convertAndSend(exchangeName, jsonRoutingKeyName, userDto);

    }
}
