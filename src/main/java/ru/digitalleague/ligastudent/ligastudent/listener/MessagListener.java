package ru.digitalleague.ligastudent.ligastudent.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@EnableRabbit
@ConditionalOnBean(value = RabbitTemplate.class)
public class MessagListener {

    @RabbitListener(queues = "${application.broker.receive-queue}")
    public void onMessage(Message message){
        log.info(new String(message.getBody(), StandardCharsets.UTF_8));
    }
}