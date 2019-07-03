package com.codecool.csepdo.punishmentservice.controller;

import com.codecool.csepdo.punishmentservice.service.PunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/punishment-service")
public class PunishmentServiceController {

    @Autowired
    private PunishmentService punishmentService;

    @GetMapping("/new-video")
    public String getNewVideo() {
        String newVideo = punishmentService.newVideo();
        if (newVideo != null) {
            return newVideo;
        } else {
            return "We're out of baaad videos for today.";
        }
    }
}
