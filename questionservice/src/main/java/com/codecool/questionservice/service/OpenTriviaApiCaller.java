package com.codecool.questionservice.service;

import com.codecool.questionservice.model.OpenTriviaQuestion;
import com.codecool.questionservice.model.OpenTriviaResponse;
import com.codecool.questionservice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenTriviaApiCaller {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${opentrivia.url}")
    private String url;

    public OpenTriviaResponse getQuestion() {
        return restTemplate.getForEntity(url, OpenTriviaResponse.class).getBody();
    }

}
