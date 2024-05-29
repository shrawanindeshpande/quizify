package com.example.quizify.service;

import ch.qos.logback.core.util.StringUtil;
import com.example.quizify.dtos.QuestionDto;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.InputMismatchException;

@Service
public class QuestionValidation {
    public void addRequestValidation(QuestionDto questionDto)throws InputMismatchException{
        if(ObjectUtils.isEmpty(questionDto)){
            throw new InputMismatchException("Please Provide Question Data To Add Question.");
        }
        if(StringUtil.isNullOrEmpty(questionDto.getQuestionTitle())){
            throw new InputMismatchException("Question title is not provided");
        }
        if(StringUtil.isNullOrEmpty(questionDto.getDifficultyLevel().toString())){
            throw new InputMismatchException("Difficulty level is not provided");
        }
        if(StringUtil.isNullOrEmpty(questionDto.getOption1())){
            throw new InputMismatchException("Option 1 is not provided");
        }
        if(StringUtil.isNullOrEmpty(questionDto.getOption2())){
            throw new InputMismatchException("Option 2 is not provided");
        }
        if(StringUtil.isNullOrEmpty(questionDto.getOption3())){
            throw new InputMismatchException("Option 3 is not provided");
        }
        if(StringUtil.isNullOrEmpty(questionDto.getOption4())){
            throw new InputMismatchException("Option 4 is not provided");
        }
        if(StringUtil.isNullOrEmpty(questionDto.getAnswer())){
            throw new InputMismatchException("Right answer is not provided");
        }

    }

    public void updateRequestValidation(QuestionDto questionDto){
        if(ObjectUtils.isEmpty(questionDto)){
            throw new InputMismatchException("Please provide data to update question.");
        }
        if(questionDto.getId()<0){
            throw new InputMismatchException("Please provide question id");
        }
    }
}
