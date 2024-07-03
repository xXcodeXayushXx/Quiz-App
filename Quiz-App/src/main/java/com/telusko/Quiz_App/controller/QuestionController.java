package com.telusko.Quiz_App.controller;

import com.telusko.Quiz_App.model.Question;
import com.telusko.Quiz_App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("Questions")
    public ResponseEntity<List<Question>> allQuestion() {
        return questionService.allQuestion();
    }

    @GetMapping("Type/{questionType}")
    public ResponseEntity<List<Question>> getQuestionByQuestionType(@PathVariable String questionType) {
        return questionService.getQuestionByQuestionType(questionType);
    }

    @PostMapping("Add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("Delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        return questionService.deleteQuestionById(id);
    }

    @PutMapping("Update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question question) {
        return questionService.updateQuestionById(id, question);
    }
}
