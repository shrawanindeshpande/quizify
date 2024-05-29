package com.example.quizify.entity;

import com.example.quizify.Enum.DifficultyLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String answer;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    private String category;

}
