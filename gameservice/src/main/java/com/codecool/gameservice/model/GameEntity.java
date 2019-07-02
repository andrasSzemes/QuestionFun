package com.codecool.gameservice.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
public class GameEntity {

    private String question;
    private List<String> answers;
}
