package org.studia.javaproadmin.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;
@Getter
@Setter
public class Question {
	private Long id;
	private String question;
	private List<Answer> answers;
	private List<Answer> correctAnswers;
	private String encodedFile;

	public void printQuestionInfo() {
		System.out.println("ID: " + id);
		System.out.println("Question: " + question);
		System.out.println("Answers: ");
		for (Answer answer : answers) {
			System.out.println(answer.getAnswer());
		}
		System.out.println("Correct Answers: ");
		for (Answer correctAnswer : correctAnswers) {
			System.out.println(correctAnswer.getAnswer());
		}
		System.out.println("Encoded File: " + encodedFile);
		System.out.println("Encoded Filee: " + encodedFile.length());
	}
}
