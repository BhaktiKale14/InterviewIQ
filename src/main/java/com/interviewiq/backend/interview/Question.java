package com.interviewiq.backend.interview;

public class Question {

    private Long id;
    private String questionText;
    private String jobRole;
    private String difficulty;

    public Question() {
    }

    public Question(Long id, String questionText, String jobRole, String difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.jobRole = jobRole;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}