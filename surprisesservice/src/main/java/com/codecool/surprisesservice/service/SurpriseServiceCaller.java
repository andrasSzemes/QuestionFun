package com.codecool.surprisesservice.service;

import com.codecool.surprisesservice.model.Surprise;
import com.codecool.surprisesservice.model.SurpriseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class SurpriseServiceCaller {
    @Value("${kittenservice.url}")
    private String kittenServiceUrl;

    @Value("${punishmentservice.url}")
    private String punishmentServiceUrl;

    @Value("${funnyimgsservice.url}")
    private String funnyimgsServiceUrl;

    @Autowired
    private RestTemplate restTemplate;


    public Surprise getSurprise(SurpriseType surpriseType) {
        try {
            switch(surpriseType) {
                case KITTEN:
                    return restTemplate.getForEntity(kittenServiceUrl, Surprise.class).getBody();
                case FUNNYIMGS:
                    return restTemplate.getForEntity(funnyimgsServiceUrl, Surprise.class).getBody();
                case PUNISHMENT:
                    return restTemplate.getForEntity(punishmentServiceUrl, Surprise.class).getBody();
                default:
                    return null;
            }
        } catch (ResourceAccessException e) {
            log.warn(e.getMessage());
            return null;
        }
    }
}
