package com.codecool.csepdo.catservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatReward {
    private String type = "catReward";
    private String src;
    private String surpriseType = "cat";

    public CatReward(String src) {
        this.src = src;
    }
}
