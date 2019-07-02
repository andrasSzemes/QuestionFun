package com.codecool.questionservice.controller;

import com.codecool.questionservice.model.Question;
import com.codecool.questionservice.service.QuestionPoolConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionPoolConnection questionPoolConnection;

    @GetMapping("/random")
    public Question getQuestion() {
        return questionPoolConnection.getQuestion();
    }
}
