package com.codecool.csepdo.funnyservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joke {

    private String category;
    private String type;
    private String joke;
    private String id;
}
