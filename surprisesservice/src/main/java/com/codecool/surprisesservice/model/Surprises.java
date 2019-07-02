package com.codecool.surprisesservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Surprises {
    private List<Surprise> surprises;

    public void addSurprise(Surprise surprise) {
        this.surprises.add(surprise);
    }
}
