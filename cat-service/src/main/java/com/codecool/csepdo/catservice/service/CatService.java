package com.codecool.csepdo.catservice.service;

import com.codecool.csepdo.catservice.model.CatReward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;

@Service
public class CatService {

    @Value("${catservice.url}")
    String url;

    @Autowired
    private RestTemplate restTemplate;


    public CatReward getRandomCat() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            headers.add("x-api-key", "2d040837-5abd-4ea3-903b-ea6079bff644");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<CatReward> reward = restTemplate.exchange(url, GET, entity, CatReward.class);
            System.out.println(reward.getBody().toString());
            return reward.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
