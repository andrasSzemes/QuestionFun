package com.codecool.surprisesservice.controller;

import com.codecool.surprisesservice.model.Surprises;
import com.codecool.surprisesservice.service.SurpriseCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surprises")
public class SurprisesController {
    @Autowired
    private SurpriseCollector surpriseCollector;


    @GetMapping("/random")
    public Surprises getSurprises(@RequestParam("correctAnswer") Boolean isCorrectAnswer) {
        return surpriseCollector.collectFor(isCorrectAnswer);
    }
}
