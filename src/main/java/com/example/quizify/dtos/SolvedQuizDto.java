package com.example.quizify.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolvedQuizDto {
    private int quizId;
    private List<String> answers;
}
