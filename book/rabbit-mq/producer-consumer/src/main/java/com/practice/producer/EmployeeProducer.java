package com.practice.producer;

import com.practice.model.Employee;
import com.practice.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmployeeProducer {

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;


    @PostMapping(value = "/send-employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee sendEmployee(@RequestBody Employee employee) {
        log.info("Sending employee to rabbitmq");
        amqpTemplate.convertAndSend(exchange, routingkey, employee);
        log.info("Sent employee to rabbitmq");
        return employee;
    }

}