package com.interviewiq.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/api/v1/home")
    public Map<String, String> home() {

        Map<String, String> response = new HashMap<>();

        response.put("application", "InterviewIQ AI");
        response.put("version", "1.0");
        response.put("developer", "Bhakti Kale");
        response.put("status", "Running Successfully 🚀");

        return response;
    }
}