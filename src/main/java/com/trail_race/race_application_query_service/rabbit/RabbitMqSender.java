package com.trail_race.race_application_query_service.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSender {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEchoMessage() {
        String message = "ECHO";
        System.out.println("Sending message:" + message);
        rabbitTemplate.send("echo", new Message(message.getBytes()));
    }
}
