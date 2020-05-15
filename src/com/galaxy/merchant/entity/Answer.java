package com.galaxy.merchant.entity;

public class Answer {
    private Sentence sentence;
    private String content;

    public Answer(Sentence sentence, String content) {
        this.sentence = sentence;
        this.content = content;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public String getContent() {
        return content;
    }
}
