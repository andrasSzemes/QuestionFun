package com.codecool.gameservice.service;

import com.codecool.gameservice.model.SurpriseEntity;
import com.codecool.gameservice.model.SurprisesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SurprisesServiceCaller {

    private String surprisesServiceURL = "http://localhost:60020/surprises/random/?correctAnswer=";

    @Autowired
    private RestTemplate restTemplate;

    public List<SurpriseEntity> getSuprises(Boolean bool) {
        List<SurpriseEntity> suprises = null;

        try { suprises = restTemplate.getForObject(surprisesServiceURL + bool.toString(), SurprisesResponse.class).getSurprises(); }
        catch (Exception e) { }

        return suprises;
    }
}
