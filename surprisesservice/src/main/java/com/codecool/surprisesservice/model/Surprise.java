package com.codecool.surprisesservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Surprise {
    private String type;
    private String src;
    private String surpriseType;
}
