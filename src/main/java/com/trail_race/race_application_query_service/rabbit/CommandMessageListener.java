package com.trail_race.race_application_query_service.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.trail_race.race_application_query_service.rabbit.data.CommandMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandMessageListener {

    private final ObjectMapper objectMapper;
    private final CommandMessageHandler commandMessageHandler;

    @RabbitListener(queues = "#{@echoQueue}", ackMode = "MANUAL")
    public void processMessage(Message message, Channel channel) throws Exception {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("Receiving message:" + messageBody);
    }

    @RabbitListener(queues = "#{@commandQueue}", ackMode = "MANUAL")
    public void processCommandMessage(Message message, Channel channel) throws Exception {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        CommandMessage commandMessage = objectMapper.readValue(message.getBody(), CommandMessage.class);
        log.info("Receiving command:" + commandMessage);
        commandMessageHandler.handleMessage(commandMessage);
    }
}
