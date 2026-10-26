package com.practice.consumer;

import com.practice.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeConsumer {

    @Value("${rabbitmq.queue}")
    String  queueName;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleMessage(Employee message) {
        log.info("Received employee from queue={}, message={}", queueName, message);
    }

}