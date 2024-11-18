package com.trail_race.race_application_query_service.query;

import com.trail_race.race_application_query_service.rabbit.RabbitMqSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {

    private final RabbitMqSender rabbitMqSender;

    public RabbitMqController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @GetMapping("/echo")
    public void sendEchoMessage() {
        rabbitMqSender.sendEchoMessage();
    }
}
