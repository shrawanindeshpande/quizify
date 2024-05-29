package com.example.quizify.dtos;

import com.example.quizify.Enum.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private int id;

    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String answer;

    private DifficultyLevel difficultyLevel;

    private String category;
}
