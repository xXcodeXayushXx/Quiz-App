package com.telusko.Quiz_App.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category; //gate2020
    private String questionType; //DSA
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @Column(columnDefinition = "text")
    private String question;
    private String answer;
}
