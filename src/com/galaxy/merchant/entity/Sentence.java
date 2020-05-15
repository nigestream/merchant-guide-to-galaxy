package com.galaxy.merchant.entity;

import com.galaxy.merchant.enums.SentenceType;

import java.util.HashMap;
import java.util.Map;

public class Sentence {
    private SentenceType sentenceType;
    private String content;


    private static final Map<SentenceType, String> setencePatternMap = new HashMap<>();

    static {
        setencePatternMap.put(SentenceType.ASSIGNED, "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$");
        setencePatternMap.put(SentenceType.CREDITS, "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$");
        setencePatternMap.put(SentenceType.QUESTION_HOW_MUCH, "^how much is (([A-Za-z\\s])+)\\?$");
        setencePatternMap.put(SentenceType.QUESTION_HOW_MANY, "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$");
    }

    public Sentence(String content) {
        this.content = content.trim();
        this.sentenceType = initSentenceType();
    }

    public String getContent() {
        return content;
    }

    private SentenceType initSentenceType() {
        SentenceType result = SentenceType.NOMATCH;
        for (SentenceType sentenceType : setencePatternMap.keySet()) {
            if (content.trim().matches(setencePatternMap.get(sentenceType))) {
                result = sentenceType;
                break;
            }
        }

        return result;
    }

    public SentenceType getSentenceType() {
        return sentenceType;
    }
}
