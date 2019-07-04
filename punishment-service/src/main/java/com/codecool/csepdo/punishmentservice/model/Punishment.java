package com.codecool.csepdo.punishmentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Punishment {

    private String type = "video";
    private String src;
    private String surpriseType = "punishment";

    public Punishment(String src) {
        this.src = src;
    }
}
