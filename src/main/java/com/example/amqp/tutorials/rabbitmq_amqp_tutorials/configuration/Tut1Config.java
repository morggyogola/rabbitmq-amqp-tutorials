package com.example.amqp.tutorials.rabbitmq_amqp_tutorials.configuration;

import com.example.amqp.tutorials.rabbitmq_amqp_tutorials.consumer.Tut1Receiver;
import com.example.amqp.tutorials.rabbitmq_amqp_tutorials.publisher.Tut1Sender;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut1", "hello-world"})
@Configuration
public class Tut1Config {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key.name}")
    private String routingKeyName;

    //Bean to define the queue
    @Bean
    public Queue queue() {
        return new Queue(queueName);
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
