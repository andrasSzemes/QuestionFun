package com.codecool.gameservice.service;

import com.codecool.gameservice.model.Question;
import com.codecool.gameservice.model.SupriseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatServiceCaller {

    private String catServiceURL = "http://localhost:60020/cats/random";
    private String alternativeJpgURL = "https://upload.wikimedia.org/wikipedia/commons/6/66/An_up-close_picture_of_a_curious_male_domestic_shorthair_tabby_cat.jpg";

    @Autowired
    private RestTemplate restTemplate;

    public SupriseEntity getCat() {
        SupriseEntity catSuprise = null;
        try { catSuprise = restTemplate.getForObject(catServiceURL, SupriseEntity.class); }
        catch (Exception e) {
            catSuprise = SupriseEntity.builder()
                    .supriseType("cat")
                    .src(alternativeJpgURL)
                    .type("jpg")
                    .build();
        }
        return catSuprise;
    }
}
