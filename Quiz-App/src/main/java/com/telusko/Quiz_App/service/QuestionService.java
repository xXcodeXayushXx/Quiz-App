package com.telusko.Quiz_App.service;

import com.telusko.Quiz_App.dao.QuestionRepository;
import com.telusko.Quiz_App.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> allQuestion() {
        try {
            Sort sortById = Sort.by("id").ascending();
            return new ResponseEntity<>(questionRepository.findAll(sortById), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByQuestionType(String questionType) {
        try {
            return new ResponseEntity<>(questionRepository.findByQuestionType(questionType), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Question(), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public ResponseEntity<String> deleteQuestionById(int id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        try {
            questionRepository.deleteById(id);
            return ResponseEntity.ok("Question with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with ID " + id + " not found.");
        }

    }


    public ResponseEntity<Question> updateQuestionById(int id, Question updatedQuestion) {
        try {
            updatedQuestion.setId(id); // Ensure the ID is set to the provided ID
            return new ResponseEntity<>(questionRepository.save(updatedQuestion), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Question(), HttpStatus.NOT_FOUND);
        }
    }
}