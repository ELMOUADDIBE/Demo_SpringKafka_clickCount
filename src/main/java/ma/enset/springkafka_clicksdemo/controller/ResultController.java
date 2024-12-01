package ma.enset.springkafka_clicksdemo.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ResultController {

    private final Map<String, Long> clickCounts = new ConcurrentHashMap<>();

    @KafkaListener(topics = "click-counts", groupId = "clicks-group")
    public void listen(ConsumerRecord<String, Long> record) {
        System.out.println("Consumed from 'click-counts': key=" + record.key() + ", value=" + record.value());
        clickCounts.put(record.key(), record.value());
    }

    @GetMapping("/api/clicks")
    @ResponseBody
    public Map<String, Long> getClickCounts() {
        return clickCounts;
    }
}
