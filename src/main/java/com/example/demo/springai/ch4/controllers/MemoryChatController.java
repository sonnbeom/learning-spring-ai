package com.example.demo.springai.ch4.controllers;

import com.example.demo.springai.ch4.services.MemoryChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class MemoryChatController {

    private final MemoryChatService memoryChatService;

    @PostMapping("/{conversationId}")
    public String chat(@PathVariable String conversationId,
                       @RequestBody String message){
        return memoryChatService.chat(conversationId, message);
    }
    @GetMapping(value = "/{conversationId}/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@PathVariable String conversationId,
                             @RequestBody String message){
        return memoryChatService.chatStream(conversationId, message);
    }
}
