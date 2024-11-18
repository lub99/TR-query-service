package com.trail_race.race_application_query_service.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trail_race.race_application_query_service.application.model.Distance;
import com.trail_race.race_application_query_service.rabbit.data.CommandMessage;
import com.trail_race.race_application_query_service.rabbit.data.CommandType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMqSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void sendEchoMessage() {
        String message = "ECHO";
        log.info("Sending message:" + message);
        rabbitTemplate.send("echo", new Message(message.getBytes()));
    }

    public void sendCreateMessage() throws JsonProcessingException {
        CommandMessage commandMessage = CommandMessage.builder()
                .commandType(CommandType.CREATE)
                .id(UUID.randomUUID().toString())
                .firstName("Mat")
                .lastName("Lub")
                .club("Rudes")
                .distance(Distance.MARATHON)
                .build();
        sendCommand(commandMessage);
    }

    public void sendDeleteMessage() throws JsonProcessingException {
        CommandMessage commandMessage = CommandMessage.builder()
                .commandType(CommandType.DELETE)
                .id("fc263db4-d71b-45ff-a0eb-266bee8ef30d")
                .build();
        sendCommand(commandMessage);
    }

    public void sendPatchMessage() throws JsonProcessingException {
        CommandMessage commandMessage = CommandMessage.builder()
                .commandType(CommandType.PATCH)
                .id("fc263db4-d71b-45ff-a0eb-266bee8ef30d")
                .lastName("Lubi")
                .build();
        sendCommand(commandMessage);
    }

    private void sendCommand(CommandMessage commandMessage) throws JsonProcessingException {
        byte[] raw = objectMapper.writer().writeValueAsString(commandMessage).getBytes(StandardCharsets.UTF_8);
        log.info("Sending command:" + commandMessage);
        rabbitTemplate.send("command", new Message(raw));
    }
}
