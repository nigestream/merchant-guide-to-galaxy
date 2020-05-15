package com.galaxy.merchant;

import com.galaxy.merchant.entity.Paragraph;
import com.galaxy.merchant.service.ParagraphService;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome, please input below and finish with a blank line");

		ParagraphService paragraphService = new ParagraphService();
		Paragraph paragraph = paragraphService.read();
		paragraph.outputAnswers();
	}

}
