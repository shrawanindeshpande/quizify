package com.example.quizify.service;

import com.example.quizify.Enum.DifficultyLevel;
import com.example.quizify.dtos.GetQuizQuestionDto;
import com.example.quizify.dtos.QuestionAnswerDto;
import com.example.quizify.dtos.QuizDto;
import com.example.quizify.entity.Question;
import com.example.quizify.entity.Quiz;
import com.example.quizify.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private static final int EASY_SCORE=1;
    private static final int MEDIUM_SCORE=3;
    private static final int HARD_SCORE=5;

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

    public List<GetQuizQuestionDto> getQuizQuestions(int id) throws ServerException {
        Optional<Quiz> optionalQuiz=quizRepository.findById(id);
        if(optionalQuiz.isEmpty()){
            throw new ServerException("Quiz with provided id does not exist");
        }
        Quiz quiz=optionalQuiz.get();
        List<GetQuizQuestionDto> quizQuestions=new ArrayList<>();
        for(Question question:quiz.getQuestionList()){
            GetQuizQuestionDto getQuizQuestionDto=new GetQuizQuestionDto(question.getId()
                    ,question.getQuestionTitle()
                    ,question.getOption1()
                    ,question.getOption2()
                    ,question.getOption3()
                    ,question.getOption4());
            quizQuestions.add(getQuizQuestionDto);
        }
        return quizQuestions;
    }

    public int calculateResult(int id, List<QuestionAnswerDto> questionAnswerDtos) {
        Quiz quiz= quizRepository.findById(id).get();
        List<Question> questions=quiz.getQuestionList();
        int idx=0;
        int score=0;
        for(QuestionAnswerDto questionAnswerDto:questionAnswerDtos){
            if(questionAnswerDto.getAnswer().equals(questions.get(idx).getAnswer())){
                score+=getScore(questions.get(idx));
            }
            idx++;
        }
        return score;
    }
    private int getScore(Question question){
        if(question.getDifficultyLevel().compareTo(DifficultyLevel.EASY)==0){
            return EASY_SCORE;
        }
        else if (question.getDifficultyLevel().compareTo(DifficultyLevel.MEDIUM)==0) {
            return MEDIUM_SCORE;
        }
        else {
            return HARD_SCORE;
        }

    }

}
