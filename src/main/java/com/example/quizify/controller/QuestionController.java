package com.example.quizify.controller;

import com.example.quizify.dtos.QuestionDto;
import com.example.quizify.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionDto questionDto){
        try{
            return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestion(),HttpStatus.OK);
    }
}
