package com.interviewiq.backend.interview;

import java.util.List;

public class Evaluation {

    private int technicalScore;
    private int communicationScore;
    private int completenessScore;
    private int overallScore;

    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> suggestions;

    public Evaluation() {
    }

    public Evaluation(
            int technicalScore,
            int communicationScore,
            int completenessScore,
            int overallScore,
            List<String> strengths,
            List<String> weaknesses,
            List<String> suggestions
    ) {
        this.technicalScore = technicalScore;
        this.communicationScore = communicationScore;
        this.completenessScore = completenessScore;
        this.overallScore = overallScore;
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.suggestions = suggestions;
    }

    public int getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(int technicalScore) {
        this.technicalScore = technicalScore;
    }

    public int getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(int communicationScore) {
        this.communicationScore = communicationScore;
    }

    public int getCompletenessScore() {
        return completenessScore;
    }

    public void setCompletenessScore(int completenessScore) {
        this.completenessScore = completenessScore;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}