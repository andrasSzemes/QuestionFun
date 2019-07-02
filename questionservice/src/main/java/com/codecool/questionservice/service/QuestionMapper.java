package com.codecool.questionservice.service;

import com.codecool.questionservice.model.OpenTriviaQuestion;
import com.codecool.questionservice.model.OpenTriviaResponse;
import com.codecool.questionservice.model.Question;
import org.springframework.stereotype.Service;

@Service
public class QuestionMapper {

    public Question map(OpenTriviaResponse openTriviaResponse) {
        Question question = new Question();
        OpenTriviaQuestion openTriviaQuestion = openTriviaResponse.getResults().get(0);
        question.setQuestion(openTriviaQuestion.getQuestion());
        question.setCorrectAnswer(openTriviaQuestion.getCorrect_answer());
        question.setIncorrectAnswers(openTriviaQuestion.getIncorrect_answers());
        return question;
    }
}
