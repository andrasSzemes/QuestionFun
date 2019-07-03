package com.codecool.questionservice.controller;

import com.codecool.questionservice.model.Question;
import com.codecool.questionservice.service.OpenTriviaApiCaller;
import com.codecool.questionservice.service.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private OpenTriviaApiCaller openTriviaApiCaller;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/random")
    public Question getQuestion() {
        try {
            return questionMapper.map(openTriviaApiCaller.getQuestion());
        } catch (ResourceAccessException e) {
            return null;
        }
    }
}
