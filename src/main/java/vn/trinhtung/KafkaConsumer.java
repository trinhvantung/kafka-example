package vn.trinhtung;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	@KafkaListener(topics = "topic-1", groupId = "group-1", containerFactory = "factory1")
	public void consumer1(String message) {
		System.out.println("Topic 1: " + message);
	}

	@KafkaListener(topics = "topic-2", groupId = "group-2", containerFactory = "factory2")
	public void consumer2(String message) {
		System.out.println("Topic 2: " + message);
	}

	@KafkaListener(topics = "topic-3", groupId = "group-3", containerFactory = "factory3")
	public void consumer3(String message) {
		System.out.println("Topic 3: " + message);
	}

	@KafkaListener(topics = "topic-4", groupId = "group-4", containerFactory = "factory4")
	public void consumer4(ConsumerRecord<String, Long> record) {
		System.out.println("Topic 4: Key = " + record.key() + " Value = " + record.value());
	}
}
