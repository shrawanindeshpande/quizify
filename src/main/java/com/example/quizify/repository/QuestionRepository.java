package com.example.quizify.repository;

import com.example.quizify.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(nativeQuery = true,value = "SELECT * FROM question q WHERE q.category= :category ORDER BY RAND() LIMIT :numberOfQuestions")
    List<Question> findRandomByCategory(String category,int numberOfQuestions);
}
