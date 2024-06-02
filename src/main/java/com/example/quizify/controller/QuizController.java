package com.example.quizify.controller;

import com.example.quizify.dtos.QuizDto;
import com.example.quizify.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<QuizDto> create(@RequestParam String category,@RequestParam int numberOfQuestions,@RequestParam String title){
        return new ResponseEntity<>(quizService.createQuiz(category,numberOfQuestions,title), HttpStatus.OK);
    }

}
