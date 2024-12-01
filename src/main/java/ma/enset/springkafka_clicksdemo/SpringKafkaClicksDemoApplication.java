package ma.enset.springkafka_clicksdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class SpringKafkaClicksDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaClicksDemoApplication.class, args);
    }

}
