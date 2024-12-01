package ma.enset.springkafka_clicksdemo.controller;

import ma.enset.springkafka_clicksdemo.KafkaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClickController {

    private final KafkaService kafkaService;

    public ClickController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/click")
    public String registerClick(@RequestParam String userId, Model model) {
        kafkaService.sendClick(userId);
        model.addAttribute("message", "Click registered for user: " + userId);
        return "index";
    }
}