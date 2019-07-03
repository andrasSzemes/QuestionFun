package com.codecool.gameservice.controller;

import com.codecool.gameservice.model.GameEntity;
import com.codecool.gameservice.model.GameResponse;
import com.codecool.gameservice.model.Question;
import com.codecool.gameservice.model.SupriseEntity;
import com.codecool.gameservice.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public GameResponse sendGameResult() {
        boolean correctness = false; //need validation based on session

        List<SupriseEntity> suprises = suprisesServiceCaller.getSuprises(correctness);

        return gameService.getGameResponse(correctness, suprises);
    }
}
