package com.galaxy.merchant.entity;

import java.util.ArrayList;
import java.util.List;

public class Paragraph {
    private List<Sentence> sentences;
    private List<Answer> answers;

    public Paragraph(List<Sentence> sentences) {
        this.sentences = sentences;
        this.answers = new ArrayList<>();
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void outputAnswers() {
        for (Answer answer : answers) {
            System.out.println(answer.getContent());
        }
    }
}
