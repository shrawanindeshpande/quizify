package com.example.quizify.controller;

import com.example.quizify.dtos.*;
import com.example.quizify.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<QuizDto> create(@RequestParam String category,@RequestParam int numberOfQuestions,@RequestParam String title){
        return new ResponseEntity<>(quizService.createQuiz(category,numberOfQuestions,title), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable int id)  {
        try {
            return new ResponseEntity<List<GetQuizQuestionDto>>(quizService.getQuizQuestions(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer>  submitQuiz(@PathVariable int id,@RequestBody List<QuestionAnswerDto> questionAnswerDtos){
        return new ResponseEntity<>(quizService.calculateResult(id,questionAnswerDtos),HttpStatus.OK);
    }

}
