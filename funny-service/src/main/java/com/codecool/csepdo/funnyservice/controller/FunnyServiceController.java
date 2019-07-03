package com.codecool.csepdo.funnyservice.controller;

import com.codecool.csepdo.funnyservice.model.Joke;
import com.codecool.csepdo.funnyservice.service.FunnyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("funny-service")
public class FunnyServiceController {

    @GetMapping("/new-joke")
    public Joke getNewJoke() {
        return FunnyService.newJoke();
    }
}
