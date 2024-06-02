package com.example.quizify.service;

import com.example.quizify.dtos.QuizDto;
import com.example.quizify.entity.Question;
import com.example.quizify.entity.Quiz;
import com.example.quizify.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionService questionService;

    public QuizDto createQuiz(String category, int numberOfQuestions, String title){
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        List<Question> questionList=questionService.getRandomQuestionByCategory(category,numberOfQuestions);
        quiz.setQuestionList(questionList);
        quiz=quizRepository.save(quiz);
        return QuizDto.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .questionList(quiz.getQuestionList())
                .build();
    }
}
