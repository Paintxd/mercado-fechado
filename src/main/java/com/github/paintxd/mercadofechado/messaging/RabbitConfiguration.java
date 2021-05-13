package com.github.paintxd.mercadofechado.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue myQueue() {
        return new Queue("mercadofechado.queue");
    }

    @Bean
    public RabbitService sender() {
        return new RabbitService();
    }

    @Bean
    public Jackson2JsonMessageConverter conveter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory conFac) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(conFac);
        rabbitTemplate.setMessageConverter(conveter());

        return rabbitTemplate;
    }
}
