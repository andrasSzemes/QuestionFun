package com.codecool.csepdo.catservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatReward {
    private int height;
    private String id;
    private String url;
    private int width;
}
