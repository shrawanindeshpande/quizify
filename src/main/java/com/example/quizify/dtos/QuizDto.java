package com.example.quizify.dtos;

import com.example.quizify.entity.Question;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizDto {

    private int id;

    private String title;

    private List<Question> questionList;

}
