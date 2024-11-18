package com.trail_race.race_application_query_service.query;

import com.trail_race.race_application_query_service.rabbit.RabbitMqSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitMqController {

    private final RabbitMqSender rabbitMqSender;

    @GetMapping("/echo")
    public void sendEchoMessage() {
        rabbitMqSender.sendEchoMessage();
    }
}
