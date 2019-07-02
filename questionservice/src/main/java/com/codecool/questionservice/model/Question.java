package com.codecool.questionservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Question {
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;
}
