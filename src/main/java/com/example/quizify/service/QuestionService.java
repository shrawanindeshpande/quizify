package com.example.quizify.service;

import ch.qos.logback.core.util.StringUtil;
import com.example.quizify.dtos.QuestionDto;
import com.example.quizify.entity.Question;
import com.example.quizify.repository.QuestionRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

@Service
@Slf4j
public class QuestionService {
    private static final String ADD_MSG="Question has been added successfully";
    @Autowired
    private QuestionValidation questionValidation;

    @Autowired
    private QuestionRepository questionRepository;

    public String addQuestion(QuestionDto questionDto) throws InputMismatchException{
        questionValidation.addRequestValidation(questionDto);
        Question question= Question.builder()
                .questionTitle(questionDto.getQuestionTitle())
                .difficultyLevel(questionDto.getDifficultyLevel())
                .option1(questionDto.getOption1())
                .option2(questionDto.getOption2())
                .option3(questionDto.getOption3())
                .option4(questionDto.getOption4())
                .answer(questionDto.getAnswer())
                .build();
        questionRepository.save(question);
        return ADD_MSG;
    }

    public List<QuestionDto> getAllQuestion(){
        List<Question> questionList=questionRepository.findAll();
        List<QuestionDto> questionDtos=new ArrayList<>();
        for(Question question:questionList){
            questionDtos.add(questionToDto(question));
        }
        return questionDtos;
    }

    private QuestionDto questionToDto(Question question){
        QuestionDto questionDto=QuestionDto.builder()
                .id(question.getId())
                .questionTitle(question.getQuestionTitle())
                .difficultyLevel(question.getDifficultyLevel())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .answer(question.getAnswer())
                .build();
        return questionDto;
    }


}
