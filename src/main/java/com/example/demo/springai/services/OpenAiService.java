package com.example.demo.springai.services;


import com.example.demo.springai.model.Answer;
import com.example.demo.springai.model.Question;

public interface OpenAiService {

    Answer getAnswer(Question question);
}
