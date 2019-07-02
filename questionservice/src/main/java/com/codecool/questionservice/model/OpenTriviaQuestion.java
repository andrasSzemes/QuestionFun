package com.codecool.questionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OpenTriviaQuestion {
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;
}
