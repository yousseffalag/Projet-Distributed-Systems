package com.falag.youssef.ebankbot.controllers;

import com.falag.youssef.ebankbot.services.EbankAIAgent;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class EbankChatbotController {

    private final EbankAIAgent aiAgent;

    public EbankChatbotController(EbankAIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam(name = "question", defaultValue = "Bonjour, quels sont les services disponibles ?") String question) {
        String response = aiAgent.chat(question);
        return Map.of(
                "question", question,
                "response", response
        );
    }

    @PostMapping("/chat")
    public Map<String, String> chatPost(@RequestBody Map<String, String> request) {
        String question = request.getOrDefault("question", "Bonjour");
        String response = aiAgent.chat(question);
        return Map.of(
                "question", question,
                "response", response
        );
    }
}
