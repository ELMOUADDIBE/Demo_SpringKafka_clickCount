package ma.enset.springkafka_clicksdemo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class KafkaStreamsService {

    @Bean
    public KStream<String, String> processClicks(StreamsBuilder builder) {
        // Read from the "clicks" topic with String values
        KStream<String, String> stream = builder.stream("clicks", Consumed.with(Serdes.String(), Serdes.String()));

        // Log the consumed messages
        stream.peek((key, value) -> System.out.println("Consumed message: key=" + key + ", value=" + value));

        // Group by key and count the clicks
        KTable<String, Long> counts = stream.groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .count();

        // Write the counts to the "click-counts"
        counts.toStream()
                .peek((key, value) -> System.out.println("Produced to 'click-counts': key=" + key + ", count=" + value))
                .to("click-counts", Produced.with(Serdes.String(), Serdes.Long()));

        return stream;
    }
}
