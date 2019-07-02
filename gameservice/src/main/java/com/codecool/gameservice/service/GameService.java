package com.codecool.gameservice.service;

import com.codecool.gameservice.model.GameEntity;
import com.codecool.gameservice.model.GameResponse;
import com.codecool.gameservice.model.Question;
import com.codecool.gameservice.model.SupriseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    public GameEntity getGameEntity(Question question) {
        GameEntity gameEntity = GameEntity.builder()
                .question(question.getQuestion())
                .answers(getSuffledAnswers(question))
                .build();

        return gameEntity;
    }

    public GameResponse getGameResponse(boolean correctness, List<SupriseEntity> suprises) {
        GameResponse gameResponse = GameResponse.builder()
                .correctAnswer(correctness)
                .suprises(suprises)
                .build();

        return gameResponse;
    }

    private List<String> getSuffledAnswers(Question question) {
        List<String> shuffledAnswers = question.getIncorrectAnswers();
        shuffledAnswers.add(question.getCorrectAnswer());
        Collections.shuffle(shuffledAnswers, new Random());

        return shuffledAnswers;
    }
}
