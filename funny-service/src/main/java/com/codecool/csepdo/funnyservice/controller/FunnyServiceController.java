package com.codecool.csepdo.funnyservice.controller;

import com.codecool.csepdo.funnyservice.model.FunnyImg;
import com.codecool.csepdo.funnyservice.service.FunnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funny-service")
public class FunnyServiceController {

    @Autowired
    private FunnyService funnyService;

    @GetMapping("/new-image")
    public FunnyImg getNewImage() {
        return funnyService.newImage();
    }
}
