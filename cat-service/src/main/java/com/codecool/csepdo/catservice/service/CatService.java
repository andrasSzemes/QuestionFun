package com.codecool.csepdo.catservice.service;

import com.codecool.csepdo.catservice.model.CatReward;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CatService {

    @Value("${catservice.url}")
    String url;

    public CatReward getRandomCat() {
        try {
            return new CatReward(url);
        } catch (Exception e) {
            return null;
        }
    }
}
