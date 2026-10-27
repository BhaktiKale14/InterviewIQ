package com.interviewiq.backend.interview;

public class Interview {

    private Long id;
    private String jobRole;
    private String difficulty;
    private int totalQuestions;

    public Interview() {
    }

    public Interview(Long id, String jobRole, String difficulty, int totalQuestions) {
        this.id = id;
        this.jobRole = jobRole;
        this.difficulty = difficulty;
        this.totalQuestions = totalQuestions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}