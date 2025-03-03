package com.crack.snap.make.spring.ai.chatbot.rest;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

import com.crack.snap.make.spring.ai.chatbot.models.HumanMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Mohan Sharma
 */
@Slf4j
@RestController
public class AiRestController {

    private final ChatClient ollamaChatClient;

    public AiRestController(ChatClient chatClient) {
        this.ollamaChatClient = chatClient;
    }

    @PostMapping("/inference")
    public Flux<String> ask(@RequestBody HumanMessage humanMessage) {
        log.info("Received message: {}", humanMessage);
        return this.ollamaChatClient
                .prompt()
                .user(humanMessage.query())
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY,
                        humanMessage.sessionId()))
                .stream()
                .content();

    }
}
