package com.example.demo.springai.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;

@Service
public class PromptRunner {
    private static final Logger log = LoggerFactory.getLogger(PromptRunner.class);

    private final ChatClient chatClient;


    public PromptRunner(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public ChatClient client(){
        return chatClient;
    }

    //user 채널만 활용
    public String user(String userText){
        return text(chatClient.prompt().user(userText).call().chatResponse());
    }

    //System + user
    public String systemAndUser(String systemText, String userText){
        return text(chatClient.prompt().system(systemText).user(userText).call().chatResponse());
    }

    //토큰 수까지 필요한 경우
    public Result userWithTokens(String userText){
        ChatResponse response = chatClient.prompt().user(userText).call().chatResponse();

        return new Result(text(response), totalTokens(response));
    }

    public Result systemAndUserWithTokens(String systemText, String userText){
        ChatResponse response = chatClient.prompt().user(userText).system(systemText).call().chatResponse();
        return new Result(text(response), totalTokens(response));
    }

    private String text(ChatResponse response) {
        var usage = response.getMetadata().getUsage();

        log.debug("model ={} tokens(prompt/completion/total) ={}/{}/{}",
                response.getMetadata().getModel(),
                usage.getPromptTokens(),
                usage.getCompletionTokens(),
                usage.getTotalTokens()
        );
        return response.getResult().getOutput().getText();
    }

    private Long totalTokens(ChatResponse response) {
        var total = response.getMetadata().getUsage().getTotalTokens();
        return total == null ? null : total.longValue();
    }


    public record Result(String answer, Long totalTokens) {
    }
}
