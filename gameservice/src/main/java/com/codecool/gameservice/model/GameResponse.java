package com.codecool.gameservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GameResponse {

    private boolean correctAnswer;
    private List<SupriseEntity> suprises;
}
