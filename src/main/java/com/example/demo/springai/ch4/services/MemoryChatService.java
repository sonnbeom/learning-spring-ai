package com.example.demo.springai.ch4.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MemoryChatService {

    private final ChatClient chatClient;

    public MemoryChatService(@Qualifier("memoryChatClient")ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public String chat(String conversationId, String Message){
        return chatClient.prompt()
                .user(Message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }


    public Flux<String> chatStream(String conversationId, String message) {
        return null;
    }
}
