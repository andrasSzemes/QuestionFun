package com.codecool.gameservice.controller;

import com.codecool.gameservice.model.GameEntity;
import com.codecool.gameservice.model.GameResponse;
import com.codecool.gameservice.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController("/game")
@Slf4j
public class GameController {

    private String questionServiceURL = "http://localhost:60010/question/random";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public GameEntity startGame() {
        Question question = restTemplate.getForObject(questionServiceURL, Question.class);

        GameEntity gameEntity = GameEntity.builder()
                .question(question.getQuestion())
                .answers(getSuffledAnswers(question))
                .build();

        return gameEntity;
    }

    private List<String> getSuffledAnswers(Question question) {
        List<String> shuffledAnswers = question.getIncorrectAnswers();
        shuffledAnswers.add(question.getCorrectAnswer());
        Collections.shuffle(shuffledAnswers, new Random());

        return shuffledAnswers;
    }
}
