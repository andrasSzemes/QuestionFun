package com.codecool.csepdo.funnyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunnyImg {

    private String type = "imgReward";
    private String src;
    private String surpriseType = "funnyImg";

    public FunnyImg(String url) {
        this.src = url;
    }
}
