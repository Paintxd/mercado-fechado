package com.github.paintxd.mercadofechado.service;

import com.github.paintxd.mercadofechado.controller.dto.PurchaseMessageDto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void send(PurchaseMessageDto purchaseMessageDto) {
        this.template.convertAndSend(queue.getName(), purchaseMessageDto);
    }
}
