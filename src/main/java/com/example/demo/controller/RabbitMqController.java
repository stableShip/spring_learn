package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@RestController
@Slf4j
public class RabbitMqController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("mq.queue.test")
    private String testQueueStr;

    @Bean(name = "testQueue")
    public Queue createTestQueue(@Value("mq.queue.test") String queueName) {
        return new Queue(queueName, true);
    }

    @GetMapping(value = "/testRabbitmq")
    private String postMessageToRabbitMq(HttpServletRequest request) {
        try {
            amqpTemplate.convertSendAndReceive(testQueueStr, "test");
        } catch (AmqpException ex) {
            ex.printStackTrace();
            // do some logic
            throw ex;
        }
        return "test";
    }

    @RabbitListener(queuesToDeclare = @org.springframework.amqp.rabbit.annotation.Queue("mq.queue.test"))
    private void testQueueReceiver(String message) {
        log.info(MessageFormat.format("{0} 被消费", message));
    }

}
