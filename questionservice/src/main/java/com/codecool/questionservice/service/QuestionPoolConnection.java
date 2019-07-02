package com.codecool.questionservice.service;

import com.codecool.questionservice.model.OpenTriviaQuestion;
import com.codecool.questionservice.model.OpenTriviaResponse;
import com.codecool.questionservice.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuestionPoolConnection {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${opentrivia.url}")
    private String url;

    public Question getQuestion() {
        Question question = new Question();
        OpenTriviaResponse response = restTemplate.getForEntity(url, OpenTriviaResponse.class).getBody();
        OpenTriviaQuestion openTriviaQuestion = response.getResults().get(0);
        question.setQuestion(openTriviaQuestion.getQuestion());
        question.setCorrectAnswer(openTriviaQuestion.getCorrect_answer());
        question.setIncorrectAnswers(openTriviaQuestion.getIncorrect_answers());
        return question;
    }

}
