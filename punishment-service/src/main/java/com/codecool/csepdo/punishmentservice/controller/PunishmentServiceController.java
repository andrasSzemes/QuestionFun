package com.codecool.csepdo.punishmentservice.controller;

import com.codecool.csepdo.punishmentservice.model.Punishment;
import com.codecool.csepdo.punishmentservice.service.PunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/punishments")
public class PunishmentServiceController {

    @Autowired
    private PunishmentService punishmentService;

    @GetMapping("/new-video")
    public Punishment getNewVideo() {
        Punishment newVideo = punishmentService.newVideo();
        if (newVideo != null) {
            return newVideo;
        } else {
            return null;
        }
    }
}
