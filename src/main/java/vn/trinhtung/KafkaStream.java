package vn.trinhtung;

import java.time.Duration;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaStream {
	@Bean
	KStream<String, String> kStream(StreamsBuilder builder) {
		KStream<String, String> stream = builder.stream("topic-1");
		stream.peek((key, value) -> {
			System.out.println(key + ": " + value);
		}).groupByKey().count(Named.as("count")).toStream().to("topic-4",
				Produced.with(Serdes.String(), Serdes.Long()));

		stream.map((key, value) -> new KeyValue<>(key, (long) value.length())).to("topic-4",
				Produced.with(Serdes.String(), Serdes.Long()));

		stream.join(builder.stream("topic-2"),
				(value1, value2) -> String.format("Value1: %s - Value2: %s", value1, value2),
				JoinWindows.ofTimeDifferenceWithNoGrace((Duration.ofSeconds(10))),
				StreamJoined.with(Serdes.String(), Serdes.String(), Serdes.String())).to("topic-3");

		return stream;
	}
}
