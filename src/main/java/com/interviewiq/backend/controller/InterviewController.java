package com.interviewiq.backend.controller;

import com.interviewiq.backend.interview.Answer;
import com.interviewiq.backend.interview.Evaluation;
import com.interviewiq.backend.interview.Question;
import com.interviewiq.backend.service.EvaluationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interview")
public class InterviewController {

    private final EvaluationService evaluationService;

    public InterviewController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {

        return List.of(
                new Question(
                        1L,
                        "What is the difference between ArrayList and LinkedList?",
                        "Java Developer",
                        "Easy"
                ),

                new Question(
                        2L,
                        "Explain the concept of inheritance in Java.",
                        "Java Developer",
                        "Medium"
                ),

                new Question(
                        3L,
                        "What is the difference between process and thread?",
                        "Software Developer",
                        "Medium"
                ),

                new Question(
                        4L,
                        "Explain REST API and HTTP methods.",
                        "Backend Developer",
                        "Easy"
                ),

                new Question(
                        5L,
                        "What is database normalization?",
                        "Backend Developer",
                        "Medium"
                )
        );
    }

    @PostMapping("/answer")
    public String submitAnswer(@RequestBody Answer answer) {

        return "Answer received for question "
                + answer.getQuestionId()
                + " successfully! 🎉";
    }

    @PostMapping("/evaluate")
    public Evaluation evaluateAnswer(@RequestBody Answer answer) {

        return evaluationService.evaluate(answer);
    }
}