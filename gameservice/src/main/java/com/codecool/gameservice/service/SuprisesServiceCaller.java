package com.codecool.gameservice.service;

import com.codecool.gameservice.model.SupriseEntity;
import com.codecool.gameservice.model.SuprisesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SuprisesServiceCaller {

    private String suprisesServiceURL = "http://localhost:60050/suprises/random/?correctAnswer=";

    @Autowired
    private RestTemplate restTemplate;

    public List<SupriseEntity> getSuprises(Boolean bool) {
        List<SupriseEntity> suprises = null;

        try { suprises = restTemplate.getForObject(suprisesServiceURL + bool.toString(), SuprisesResponse.class).getSuprises(); }
        catch (Exception e) { }

        return suprises;
    }
}
