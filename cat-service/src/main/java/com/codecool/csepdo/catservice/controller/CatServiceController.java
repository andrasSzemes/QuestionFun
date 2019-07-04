package com.codecool.csepdo.catservice.controller;

import com.codecool.csepdo.catservice.model.CatReward;
import com.codecool.csepdo.catservice.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat")
public class CatServiceController {

    @Autowired
    private CatService catService;

    @GetMapping("/random")
    public CatReward randomCat() {
        return catService.getRandomCat();
    }
}
