package vn.trinhtung;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	@Bean
	NewTopic topic1() {
		return TopicBuilder.name("topic-1").partitions(2).build();
	}

	@Bean
	NewTopic topic2() {
		return TopicBuilder.name("topic-2").partitions(2).build();
	}

	@Bean
	NewTopic topic3() {
		return TopicBuilder.name("topic-3").partitions(2).build();
	}

	@Bean
	NewTopic topic4() {
		return TopicBuilder.name("topic-4").partitions(2).build();
	}
}
