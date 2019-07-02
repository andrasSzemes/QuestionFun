package com.codecool.csepdo.catservice.controller;

import com.codecool.csepdo.catservice.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cat")
public class CatServiceController {

    @Autowired
    private CatService catService;

    @GetMapping("/random")
    public String randomCat() {
        if (catService.getRandomCat().getUrl() != null) {
            return catService.getRandomCat().getUrl();
        } else {
            return "Sorry, we're out of cute cats today.";
        }
    }

}
