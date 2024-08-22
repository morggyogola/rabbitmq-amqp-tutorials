package com.example.amqp.tutorials.rabbitmq_amqp_tutorials.configuration;

import com.example.amqp.tutorials.rabbitmq_amqp_tutorials.consumer.Tut1Receiver;
import com.example.amqp.tutorials.rabbitmq_amqp_tutorials.publisher.Tut1Sender;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1", "hello-world"})
@Configuration
public class Tut1Config {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKeyName;

    @Value("${rabbitmq.routing.key.json.name}")
    private String jsonRoutingKeyName;

    //Bean to define the queue
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName);
    }


    //Bean to define the exchange(links the producer and the queue using a routing key)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    //Binding between queue and exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).
                to(exchange())
                .with(routingKeyName);
    }
    @Bean
    public Binding jsonbinding() {
        return BindingBuilder.bind(jsonQueue()).
                to(exchange())
                .with(jsonRoutingKeyName);
    }
    //converter from string to Json
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    //create a custom bean for json messages
    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //---------------Predefined Beans----------------//

                    //ConnectionFactory

                    //RabbitTemplate

                    //RabbitAdmin
    //----------------------------------------------//

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}
