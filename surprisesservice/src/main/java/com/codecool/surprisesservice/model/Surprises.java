package com.codecool.surprisesservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Surprises {

    @Builder.Default
    private List<Surprise> surprises = new ArrayList<>();

    public void addSurprise(Surprise surprise) {
        this.surprises.add(surprise);
    }
}
