package com.trail_race.race_application_query_service.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqSender {

    private final RabbitTemplate rabbitTemplate;

    public void sendEchoMessage() {
        String message = "ECHO";
        log.info("Sending message:" + message);
        rabbitTemplate.send("echo", new Message(message.getBytes()));
    }
}
