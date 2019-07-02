package com.codecool.gameservice.service;

import com.codecool.gameservice.model.Question;
import com.codecool.gameservice.model.SupriseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatServiceCaller {

    private String catServiceURL = "http://localhost:60020/cats/random";

    @Autowired
    private RestTemplate restTemplate;

    public SupriseEntity getCat() {
        return restTemplate.getForObject(catServiceURL, SupriseEntity.class);
    }
}
