package com.codecool.gameservice.controller;

import com.codecool.gameservice.model.*;
import com.codecool.gameservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController("/game")
@Slf4j
public class GameController {

    private HashMap<String, String> storage = new HashMap<>();

    @Autowired
    private GameService gameService;

    @Autowired
    private QuestionServiceCaller questionServiceCaller;

    @Autowired
    private SuprisesServiceCaller suprisesServiceCaller;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public GameEntity startGame() {
        Question question = questionServiceCaller.getQuestion();
        storage.put(question.getQuestion(), question.getCorrectAnswer());

        return gameService.getGameEntity(question);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public GameResponse sendGameResult(@RequestBody GameRequest gameRequest) {
        String correctAnswer = storage.get(gameRequest.getQuestion());
        boolean correctness = correctAnswer.equals(gameRequest.getSelectedAnswer());

        List<SupriseEntity> suprises = suprisesServiceCaller.getSuprises(correctness);

        return gameService.getGameResponse(correctness, suprises);
    }
}
