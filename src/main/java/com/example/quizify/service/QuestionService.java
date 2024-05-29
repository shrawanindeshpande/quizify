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
import java.util.Optional;

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
                .category(questionDto.getCategory())
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

    public List<QuestionDto> getByCategory(String category)throws InputMismatchException{
        List<Question> questionList=questionRepository.findByCategory(category);
        if(questionList.isEmpty()){
            throw new InputMismatchException("There are no question under given category.");
        }
        List<QuestionDto> questionDtos=new ArrayList<>();
        for(Question question:questionList){
            questionDtos.add(questionToDto(question));
        }
        return questionDtos;
    }

    public QuestionDto update(QuestionDto questionDto){
        questionValidation.updateRequestValidation(questionDto);
        Optional<Question> questionOptional=questionRepository.findById(questionDto.getId());
        if(questionOptional.isEmpty()){
            throw new InputMismatchException("Question with given id does not exist.");
        }
        Question question=questionOptional.get();
        if(!StringUtil.isNullOrEmpty(questionDto.getQuestionTitle())){
            question.setQuestionTitle(questionDto.getQuestionTitle());
        }
        if(questionDto.getDifficultyLevel()!=null){
            question.setDifficultyLevel(questionDto.getDifficultyLevel());
        }
        if(!StringUtil.isNullOrEmpty(questionDto.getCategory())){
            question.setCategory(questionDto.getCategory());
        }
        if(!StringUtil.isNullOrEmpty(questionDto.getOption1())){
            question.setOption1(questionDto.getOption1());
        }
        if(!StringUtil.isNullOrEmpty(questionDto.getOption2())){
            question.setOption2(questionDto.getOption2());
        }
        if(!StringUtil.isNullOrEmpty(questionDto.getOption3())){
            question.setOption3(questionDto.getOption3());
        }
        if(!StringUtil.isNullOrEmpty(questionDto.getOption4())){
            question.setOption4(questionDto.getOption4());
        }
        if(!StringUtil.isNullOrEmpty(questionDto.getAnswer())){
            question.setAnswer(questionDto.getAnswer());
        }
        if(!StringUtil.isNullOrEmpty(questionDto.getCategory())){
            question.setCategory(questionDto.getCategory());
        }
        question=questionRepository.save(question);
        questionDto=questionToDto(question);
        return questionDto;
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
                .category(question.getCategory())
                .build();
        return questionDto;
    }


}
