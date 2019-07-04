package com.codecool.gameservice.service;

import com.codecool.gameservice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuestionServiceCaller {

    private String questionServiceURL = "http://question-service/questions/random";

    @Autowired
    private RestTemplate restTemplate;

    public Question getQuestion() {
        return restTemplate.getForObject(questionServiceURL, Question.class);
    }
}
