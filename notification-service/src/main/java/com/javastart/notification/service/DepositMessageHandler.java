package com.javastart.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.notification.config.RabbitMqConfig;
import com.javastart.notification.dto.DepositRs;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositMessageHandler {

    private final JavaMailSender javaMailSender;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_DEPOSIT)
    public void receive(Message message) throws JsonProcessingException {
        System.out.println(message);
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();
        DepositRs depositRs = objectMapper.readValue(jsonBody, DepositRs.class);
        System.out.println(depositRs);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(depositRs.getEmail());

        mailMessage.setFrom("ADMIN@cat.com");

        mailMessage.setSubject("Deposit");
        mailMessage.setText("Make deposit, sum: " + depositRs.getAmount());

        javaMailSender.send(mailMessage);
    }

}
