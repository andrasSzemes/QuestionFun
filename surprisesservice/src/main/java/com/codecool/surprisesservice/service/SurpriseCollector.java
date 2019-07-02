package com.codecool.surprisesservice.service;

import com.codecool.surprisesservice.model.SurpriseType;
import com.codecool.surprisesservice.model.Surprises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurpriseCollector {
    @Autowired
    private SurpriseServiceCaller surpriseServiceCaller;

    public Surprises collectFor(Boolean isCorrectAnswer) {
        Surprises surprises = new Surprises();
        if(isCorrectAnswer) {
            surprises.addSurprise(surpriseServiceCaller.getSurprise(SurpriseType.KITTEN));
            surprises.addSurprise(surpriseServiceCaller.getSurprise(SurpriseType.FUNNYIMGS));
        } else {
            surprises.addSurprise(surpriseServiceCaller.getSurprise(SurpriseType.PUNISHMENT));
        }
        return surprises;
    }
}
