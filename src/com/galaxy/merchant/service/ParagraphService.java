package com.galaxy.merchant.service;

import com.galaxy.merchant.entity.Answer;
import com.galaxy.merchant.entity.Paragraph;
import com.galaxy.merchant.entity.Sentence;
import com.galaxy.merchant.enums.Error;

import java.util.*;


public class ParagraphService {

    private Scanner scan;
    private SentenceService sentenceService;


    public ParagraphService() {
        this.scan = new Scanner(System.in);
        this.sentenceService = new SentenceService();
    }

    public Paragraph read() {
        String line;
        List<Sentence> sentences = new ArrayList<>();
        while (this.scan.hasNextLine() && (line = this.scan.nextLine()).length() > 0) {
            sentences.add(new Sentence(line));
        }

        if (sentences.size() < 1) {
            Error.NO_INPUT.printMessage();
        }

        Paragraph paragraph = new Paragraph(sentences);

        Map<String, String> constantAssignments = new HashMap<>();
        Map<String, Float> mineralCredits = new HashMap<>();

        for (Sentence sentence : paragraph.getSentences()) {
            String output = sentenceService.translate(sentence, constantAssignments, mineralCredits);
            paragraph.addAnswer(new Answer(sentence, output));
        }

        return paragraph;
    }


}
