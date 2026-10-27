package com.interviewiq.backend.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.interviewiq.backend.interview.Answer;
import com.interviewiq.backend.interview.Evaluation;
import org.springframework.stereotype.Service;
import com.google.genai.errors.ServerException;
import com.interviewiq.backend.exception.AiServiceUnavailableException;
import java.util.Arrays;

@Service
public class EvaluationService {

    private final Client client;

    public EvaluationService() {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GEMINI_API_KEY environment variable is not set."
            );
        }

        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public Evaluation evaluate(Answer answer) {

        String text = answer.getAnswerText();

        if (text == null || text.trim().isEmpty()) {

            return new Evaluation(
                    0,
                    0,
                    0,
                    0,
                    Arrays.asList("No answer provided."),
                    Arrays.asList("The candidate did not provide an answer."),
                    Arrays.asList("Please provide an answer to the interview question.")
            );
        }

        String prompt = """
                You are an expert technical interviewer.

                Evaluate the candidate's answer to an interview question.

                Candidate answer:
                %s

                Give scores from 0 to 10 for:
                1. Technical knowledge
                2. Communication
                3. Completeness

                Then calculate an overall score from 0 to 10.

                Also provide:
                - 2 strengths
                - 2 weaknesses
                - 2 suggestions for improvement

                Format your response exactly like this:

                TECHNICAL: <number>
                COMMUNICATION: <number>
                COMPLETENESS: <number>
                OVERALL: <number>
                STRENGTHS: <item 1> | <item 2>
                WEAKNESSES: <item 1> | <item 2>
                SUGGESTIONS: <item 1> | <item 2>
                """.formatted(text);

        GenerateContentResponse response;

try {
    response = client.models.generateContent(
            "gemini-3.6-flash",
            prompt,
            null
    );
} catch (ServerException e) {
    throw new AiServiceUnavailableException(
            "AI evaluation is temporarily unavailable. Please try again.",
            e
    );
}

        String result = response.text();

        int technicalScore = extractScore(result, "TECHNICAL:");
        int communicationScore = extractScore(result, "COMMUNICATION:");
        int completenessScore = extractScore(result, "COMPLETENESS:");
        int overallScore = extractScore(result, "OVERALL:");

        return new Evaluation(
                technicalScore,
                communicationScore,
                completenessScore,
                overallScore,
                extractList(result, "STRENGTHS:"),
                extractList(result, "WEAKNESSES:"),
                extractList(result, "SUGGESTIONS:")
        );
    }

private int extractScore(String result, String label) {
    try {
        int labelIndex = result.indexOf(label);

        if (labelIndex == -1) {
            return 0;
        }

        int start = labelIndex + label.length();
        int end = result.indexOf("\n", start);

        if (end == -1) {
            end = result.length();
        }

        String scoreText = result.substring(start, end).trim();

        java.util.regex.Matcher matcher =
                java.util.regex.Pattern
                        .compile("\\d+(?:\\.\\d+)?")
                        .matcher(scoreText);

        if (matcher.find()) {
            double score = Double.parseDouble(matcher.group());
            return Math.max(0, Math.min(10, (int) Math.round(score)));
        }

        return 0;
    } catch (Exception e) {
        return 0;
    }
}


    private java.util.List<String> extractList(
            String result,
            String label
    ) {

        try {

            int start = result.indexOf(label) + label.length();
            int end = result.indexOf("\n", start);

            if (end == -1) {
                end = result.length();
            }

            String content = result.substring(start, end).trim();

            return Arrays.asList(content.split("\\s*\\|\\s*"));

        } catch (Exception e) {

            return Arrays.asList("Not available.");
        }
    }
}