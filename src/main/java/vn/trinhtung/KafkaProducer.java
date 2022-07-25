package vn.trinhtung;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducer {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping("/send/{topic}")
	public void sendMessage(@PathVariable String topic) {
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Integer l = s.length();
		Integer length = new Random().nextInt(5, 31);
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < length; j++) {
			Integer index = new Random().nextInt(0, l);
			sb.append(s.charAt(index));
		}

		String message = sb.toString();
		String key = message.length() % 2 == 0 ? "2" : "1";

		key = "1";

		if (topic.equals("1")) {
			kafkaTemplate.send("topic-1", key, message);
		} else {
			kafkaTemplate.send("topic-2", key, message);
		}
	}
}
