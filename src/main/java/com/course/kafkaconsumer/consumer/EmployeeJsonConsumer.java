package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
//@Service
public class EmployeeJsonConsumer
{
    @KafkaListener(topics = "t_employee")
    public void consume(String message)
    {
        Employee employee = null;
        try {
            employee = new ObjectMapper().readValue(message, Employee.class);
        }
        catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("Consumed employee: {}", employee);
    }
}
