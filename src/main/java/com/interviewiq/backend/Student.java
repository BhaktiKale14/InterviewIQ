package com.interviewiq.backend;

public class Student {

    private String name;
    private String course;
    private String goal;

    public Student(String name, String course, String goal) {
        this.name = name;
        this.course = course;
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getGoal() {
        return goal;
    }
}