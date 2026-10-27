package com.interviewiq.backend.interview;

public class Answer {

    private Long questionId;
    private String answerText;

    public Answer() {
    }

    public Answer(Long questionId, String answerText) {
        this.questionId = questionId;
        this.answerText = answerText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}