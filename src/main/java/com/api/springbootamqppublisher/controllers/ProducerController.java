package com.api.springbootamqppublisher.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/producer")
public class ProducerController {
    // Aplicação precisa receber requisição GET e enviar mensagem para routing key
    // 'routingkey123'.

    @Autowired
    private RabbitTemplate rabbiteTemplate;

    Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @GetMapping
    public void publishMessage() {

        String exchangeName = "direct_logs";
        rabbiteTemplate.setExchange(exchangeName);

        String messageString = "this is a message";
        Message message = new Message(messageString.getBytes());

        String routingKey = "routingkey123";
        rabbiteTemplate.send(routingKey, message);

        logger.info("Message sent to:\nExchange:\t " + exchangeName + "\nRouting Key:\t" + routingKey);
    }
}