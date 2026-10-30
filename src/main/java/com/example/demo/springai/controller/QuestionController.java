package com.example.demo.springai.controller;

import com.example.demo.springai.model.Answer;
import com.example.demo.springai.model.Question;
import com.example.demo.springai.services.OpenAiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAiService openAiService;

    public QuestionController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question){
        return openAiService.getAnswer(question);
    }
}
