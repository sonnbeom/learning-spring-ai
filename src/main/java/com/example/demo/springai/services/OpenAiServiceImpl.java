package com.example.demo.springai.services;

import com.example.demo.springai.model.Answer;
import com.example.demo.springai.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImpl implements OpenAiService{

    private final ChatModel chatModel;

    @Value("${spring.ai.openai.chat.model: 기본값 사용}")
    private String modelName;

    public OpenAiServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getAnswer(Question question) {
        System.out.println("현재 사용하는 chatModel 구현체: " + chatModel.getClass().getName());
        System.out.println("현재 설정된 OpenAI 모델" + modelName);

        // 문자열 틀에 값을 채워주는 도구
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        //AI에게 보낼 내용을 담은 Spring 객체
        Prompt prompt = promptTemplate.create();

        //LLM에게 요청을 보내는 부분 ChatModel은 인터페이스이고 구현체를 빈으로 자동 등록
        ChatResponse chatResponse = chatModel.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getText());
    }
}
