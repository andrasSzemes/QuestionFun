package com.codecool.gameservice.service;

import com.codecool.gameservice.model.SupriseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FunnyImgServiceCaller {

    private String funnyImgServiceURL = "http://localhost:60030/funnyimgs/random";

    @Autowired
    private RestTemplate restTemplate;

    public SupriseEntity getImg() {
        return restTemplate.getForObject(funnyImgServiceURL, SupriseEntity.class);
    }
}
