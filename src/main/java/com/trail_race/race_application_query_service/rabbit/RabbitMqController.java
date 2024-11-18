package com.trail_race.race_application_query_service.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    @GetMapping("/create")
    public void sendCreateMessage() throws JsonProcessingException {
        rabbitMqSender.sendCreateMessage();
    }

    @GetMapping("/delete")
    public void sendDeleteMessage() throws JsonProcessingException {
        rabbitMqSender.sendDeleteMessage();
    }

    @GetMapping("/patch")
    public void sendPatchMessage() throws JsonProcessingException {
        rabbitMqSender.sendPatchMessage();
    }
}
