package com.telusko.Quiz_App.service;

import com.telusko.Quiz_App.dao.QuestionRepository;
import com.telusko.Quiz_App.dao.QuizRepository;
//import com.telusko.Quiz_App.dao.ResponseRepository;
import com.telusko.Quiz_App.model.Question;
import com.telusko.Quiz_App.model.QuestionWrapper;
import com.telusko.Quiz_App.model.Quiz;
import com.telusko.Quiz_App.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
//    @Autowired
//    ResponseRepository responseRepository;

    public ResponseEntity<Quiz> createQuiz(String category, int numberOfQuestions, String title) {
        try {
            //create question
            List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numberOfQuestions);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Quiz(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestionType(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4(), q.getQuestion());
            questionsForUser.add(questionWrapper);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);

        if (quizOptional.isPresent())
        {
            Quiz quiz = quizOptional.get();
            List<Question> questions = quiz.getQuestions();

            // Calculate result based on responses and questions
            int score = 0;
            int i = 0;

            for (Response response : responses) {
                if (response.getResponseAnswer().equals(questions.get(i).getAnswer())) {
                    score++;
                    i++;
                }
            }
            return new ResponseEntity<Integer>(score, HttpStatus.OK);
        }
        // Quiz not found with given id
        return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);
    }

/*    public HttpStatus storeResponses(Integer id, List<Response> responses) {
        try {
            responseRepository.save();
            return HttpStatus.OK;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }*/
}
