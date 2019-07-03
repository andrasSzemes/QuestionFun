package com.codecool.csepdo.funnyservice.controller;

import com.codecool.csepdo.funnyservice.service.FunnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funny-service")
public class FunnyServiceController {

    @Autowired
    private FunnyService funnyService;

    @GetMapping("/new-joke")
    public String getNewJoke() {
        String newImage = funnyService.newImage();
        if (newImage != null) {
            return newImage;
        } else {
            return "Sorry, no funny images for today.";
        }
    }
}
