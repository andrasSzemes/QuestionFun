package com.codecool.gameservice.service;

import com.codecool.gameservice.model.SupriseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FunnyImgServiceCaller {

    private String funnyImgServiceURL = "http://localhost:60030/funnyimgs/random";
    private String alternativeJpgURL = "https://www.unilad.co.uk/wp-content/uploads/2018/10/disaster-girl-thats-meme-fb.jpg";

    @Autowired
    private RestTemplate restTemplate;

    public SupriseEntity getImg() {
        SupriseEntity funnyImg = null;
        try { funnyImg = restTemplate.getForObject(funnyImgServiceURL, SupriseEntity.class); }
        catch (Exception e) {
            funnyImg = SupriseEntity.builder()
                    .supriseType("funnyImg")
                    .src(alternativeJpgURL)
                    .type("jpg")
                    .build();
        }

        return funnyImg;
    }
}
