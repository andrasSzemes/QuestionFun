package com.codecool.questionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OpenTriviaResponse {
    private List<OpenTriviaQuestion> results;
}
