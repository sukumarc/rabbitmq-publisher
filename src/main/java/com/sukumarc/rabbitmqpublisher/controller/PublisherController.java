package com.sukumarc.rabbitmqpublisher.controller;

import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sukumarc.rabbitmq.model.Employee;

@RestController
@RequestMapping("/rabbitmq/publish")
public class PublisherController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@GetMapping("name/{name}")
	public String publish(@PathVariable("name") String name) {
		
		Random rand = new Random();
		
		Employee p = new Employee(rand.nextInt(), name);
		rabbitTemplate.convertAndSend("Queue1", p);
		
		return "Successfully added in to the Queue";
	}
	

}
