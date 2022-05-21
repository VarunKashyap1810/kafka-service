package com.kafka.service.controller;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping(value= "/publish/{key}/{message}")
	public String sendMessage(@PathVariable("key") String key, @PathVariable("message") String message) {
        
	    ListenableFuture<SendResult<String, String>> future = 
	      kafkaTemplate.send("Kafka-Practice", key, message);
		
	    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
	    	

	        @Override
	        public void onSuccess(SendResult<String, String> result) {
	            System.out.println("Sent message=[" + message + 
	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
	            System.out.println("Unable to send message=[" 
	              + message + "] due to : " + ex.getMessage());
	        }
	    });
	    
	    return "Sent message=[" + message + "]";
	}
	
	@GetMapping(value="/publish/sensorproducer")
	public void sensorProducer(){
		
		String topicName = "SensorTopic1";

	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    props.put("partitioner.class", "com.kafka.service.controller.SensorPartitioner");
	    props.put("speed.sensor.name", "TSS");

	    Producer<String, String> producer = new KafkaProducer <>(props);

	       for (int i=0 ; i<10 ; i++)
	       producer.send(new ProducerRecord<>(topicName,"SSP"+i,"500"+i));

	       for (int i=0 ; i<10 ; i++)
	       producer.send(new ProducerRecord<>(topicName,"TSS","500"+i));

	       producer.close();

	        System.out.println("SimpleProducer Completed.");
		
	}
	
	
}
