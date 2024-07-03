package com.telusko.Quiz_App.dao;

import com.telusko.Quiz_App.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
