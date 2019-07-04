package com.codecool.csepdo.catservice.service;

import com.codecool.csepdo.catservice.model.CatReward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;

import static org.springframework.http.HttpMethod.GET;

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
