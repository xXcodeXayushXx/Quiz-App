package com.telusko.Quiz_App.controller;

import com.telusko.Quiz_App.model.QuestionWrapper;
import com.telusko.Quiz_App.model.Quiz;
import com.telusko.Quiz_App.model.Response;
import com.telusko.Quiz_App.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("Create")
    public ResponseEntity<Quiz> createQuiz(
            @RequestParam String category,
            @RequestParam int numberOfQuestions,
            @RequestParam String title) {
        return quizService.createQuiz(category, numberOfQuestions, title);
    }

    @GetMapping("Get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id)
    {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("Submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
//        HttpStatus storeStatus = quizService.storeResponses(id,responses);
        return quizService.calculateResult(id,responses);

    }

}
