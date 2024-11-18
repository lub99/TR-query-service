package com.trail_race.race_application_query_service.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class CommandMessageListener {

    @RabbitListener(queues = "#{@echoQueue}", ackMode = "MANUAL")
    public void processMessage(Message message, Channel channel) throws Exception {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("Receiving message:" + messageBody);
    }
}
