package com.telusko.Quiz_App.dao;

import com.telusko.Quiz_App.model.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findAll(Sort sort);
    List<Question> findByQuestionType(String questionType);

//    @Query(value = "SELECT * FROM question q WHERE q.category =: category ORDER BY RANDOM() LIMIT := numberOfQuestions", nativeQuery = true)
//    List<Question> findRandomQuestionsByCategory(String category, int numberOfQuestions);
    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numberOfQuestions") int numberOfQuestions);

}
