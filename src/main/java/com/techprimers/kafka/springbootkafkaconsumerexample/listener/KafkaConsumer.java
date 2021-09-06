package com.techprimers.kafka.springbootkafkaconsumerexample.listener;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {
	  
	 
	  
	  @KafkaListener(topics = "Kafka_Example", groupId = "group_json", containerFactory = "kafkaLister"
	           )
	    public void consumeJson(User user) {
	        System.out.println("Consumed JSON Message: " + user);
	    }
	  
	  
	  
	  

}
