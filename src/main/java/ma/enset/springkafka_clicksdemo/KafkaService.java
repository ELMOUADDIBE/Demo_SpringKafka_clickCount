package ma.enset.springkafka_clicksdemo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClick(String userId) {
        kafkaTemplate.send("clicks", userId, "click");
        System.out.println("Message envoyé au topic 'clicks': userId=" + userId + ", value=click");
    }

}

