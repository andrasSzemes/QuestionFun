package com.codecool.surprisesservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Surprise {
    private String type;
    private String src;
    private String surpriseType;
}
