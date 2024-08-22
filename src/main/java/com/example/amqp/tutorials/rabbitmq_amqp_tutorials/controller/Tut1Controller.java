package com.example.amqp.tutorials.rabbitmq_amqp_tutorials.controller;

import com.example.amqp.tutorials.rabbitmq_amqp_tutorials.dto.UserDto;
import com.example.amqp.tutorials.rabbitmq_amqp_tutorials.publisher.Tut1Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class Tut1Controller {
    @Autowired
    Tut1Sender producer;

    @GetMapping("/publish")
    private ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.send(message);
        return ResponseEntity.ok("Message sent to RabbitMq");
    }

    @PostMapping("/publish")
    private ResponseEntity<String> sendMessage(@RequestBody UserDto userDto) {
        producer.sendJson(userDto);
        return ResponseEntity.ok("Json Message sent to RabbitMq");
    }
}
