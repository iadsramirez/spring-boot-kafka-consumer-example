package com.techprimers.kafka.springbootkafkaconsumerexample.config;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@EnableKafka
@Configuration
public class KafkaConfiguration {
	
	  
	 @Bean
	    public ConsumerFactory<String, User> consumerFactory() {
	        
	        Map<String, Object> config = new HashMap<>();
	        
	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	        config.put(JsonSerializer.TYPE_MAPPINGS, "user:com.techprimers.kafka.springbootkafkaconsumerexample.model.User");
	        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.techprimers.kafka.springbootkafkaconsumerexample.model.User");
	        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
	        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
	        
	        return new DefaultKafkaConsumerFactory<String, User>(config, new StringDeserializer(), new JsonDeserializer<>(User.class));
	    }
	    
	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, User> kafkaLister() {
	        
	        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        
	        factory.setMissingTopicsFatal(false);
	        
	        factory.setConsumerFactory(consumerFactory());
	        
	        return factory;
	    }  
	

}
