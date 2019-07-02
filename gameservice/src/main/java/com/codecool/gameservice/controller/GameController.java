package com.codecool.gameservice.controller;

import com.codecool.gameservice.model.GameEntity;
import com.codecool.gameservice.model.GameResponse;
import com.codecool.gameservice.model.Question;
import com.codecool.gameservice.model.SupriseEntity;
import com.codecool.gameservice.service.CatServiceCaller;
import com.codecool.gameservice.service.FunnyImgServiceCaller;
import com.codecool.gameservice.service.GameService;
import com.codecool.gameservice.service.QuestionServiceCaller;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController("/game")
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private QuestionServiceCaller questionServiceCaller;

    @Autowired
    private CatServiceCaller catServiceCaller;

    @Autowired
    private FunnyImgServiceCaller funnyImgServiceCaller;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public GameEntity startGame() {
        Question question = questionServiceCaller.getQuestion();
        //need to store the correct answer
        return gameService.getGameEntity(question);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public GameResponse sendGameResult() {
        boolean correctness = true; //need validation based on session

        List<SupriseEntity> suprises = new ArrayList<>();
        if (correctness) {
            //get two types of reward
            SupriseEntity catSuprise = null;
            try { catSuprise = catServiceCaller.getCat(); }
            catch (Exception e) { }

            SupriseEntity imgSuprise = null;
            try { imgSuprise = funnyImgServiceCaller.getImg(); }
            catch (Exception e) { }

            suprises.add(catSuprise);
            suprises.add(imgSuprise);
        }
        else {
            //get a punishment
        }

        GameResponse gameResponse = GameResponse.builder()
                .correctAnswer(correctness)
                .suprises(suprises)
                .build();

        return gameResponse;
    }
}
