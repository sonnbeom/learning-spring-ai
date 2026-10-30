package com.example.demo.springai.runner;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AiRunner implements CommandLineRunner {

    private final ChatClient chatClient;

    public AiRunner(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    // prompt -> user/system -> call/stream -> content/entity
    public void run(String... args) throws Exception {
        String answer = chatClient.prompt()
                .user("spring ai를 한 문장으로 요약해줘")
                .call()
                .content();

        System.out.println(answer);
    }
}
