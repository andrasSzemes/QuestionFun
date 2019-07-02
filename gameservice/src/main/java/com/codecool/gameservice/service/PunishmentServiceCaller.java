package com.codecool.gameservice.service;

import com.codecool.gameservice.model.SupriseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PunishmentServiceCaller {

    private String punishmentServiceURL = "http://localhost:60040/punishments/random";
    private String alternativeJpgURL = "https://memestatic.fjcdn.com/pictures/Bad_027fcf_6561414.jpg";

    @Autowired
    private RestTemplate restTemplate;

    public SupriseEntity getPunishment() {
        SupriseEntity punishment = null;
        try { punishment = restTemplate.getForObject(punishmentServiceURL, SupriseEntity.class); }
        catch (Exception e) {
            punishment = SupriseEntity.builder()
                    .supriseType("punishment")
                    .src(alternativeJpgURL)
                    .type("jpg")
                    .build();
        }
        return punishment;
    }
}
