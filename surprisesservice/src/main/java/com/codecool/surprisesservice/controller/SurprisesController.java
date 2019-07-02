package com.codecool.surprisesservice.controller;

import com.codecool.surprisesservice.model.SurpriseType;
import com.codecool.surprisesservice.model.Surprises;
import com.codecool.surprisesservice.service.SurpriseServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surprises")
public class SurprisesController {
    @Autowired
    private SurpriseServiceCaller surpriseServiceCaller;


    @GetMapping("/random")
    public Surprises getSurprises(@RequestParam("correctAnswer") Boolean correctAnswer) {
        Surprises surprises = new Surprises();
        if(correctAnswer) {
            surprises.addSurprise(surpriseServiceCaller.getSurprise(SurpriseType.KITTEN));
            surprises.addSurprise(surpriseServiceCaller.getSurprise(SurpriseType.FUNNYIMGS));
        } else {
            surprises.addSurprise(surpriseServiceCaller.getSurprise(SurpriseType.PUNISHMENT));
        }
        return surprises;
    }
}