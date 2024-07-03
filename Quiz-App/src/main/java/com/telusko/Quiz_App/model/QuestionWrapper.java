package com.telusko.Quiz_App.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {

    private int id;
//    private String category;
    private String questionType;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @Column(columnDefinition = "text")
    private String question;
//    private String answer;
}
