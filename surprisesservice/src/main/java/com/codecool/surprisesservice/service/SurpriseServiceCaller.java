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
        Surprise surprise = null;
        switch(surpriseType) {
            case KITTEN:
                try { surprise = restTemplate.getForEntity(kittenServiceUrl, Surprise.class).getBody(); }
                catch (ResourceAccessException e) {
                    if (surprise == null) { surprise = SurpriseType.KITTEN.getDefault(); }
                }
            case FUNNYIMGS:
                try { surprise = restTemplate.getForEntity(funnyimgsServiceUrl, Surprise.class).getBody(); }
                catch (ResourceAccessException e) {
                    if (surprise == null) { surprise = SurpriseType.FUNNYIMGS.getDefault(); }
                }
            case PUNISHMENT:
                try {surprise = restTemplate.getForEntity(punishmentServiceUrl, Surprise.class).getBody(); }
                catch (ResourceAccessException e) {
                    if (surprise == null) { surprise = SurpriseType.PUNISHMENT.getDefault(); }
                }
        }
        return surprise;
    }
}
