package org.studia.javaproadmin.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Question {
	private Long id;
	private String question;
	private List<Answer> answers;
	private List<Answer> correctAnswers;
	private String encodedFile;

	public static List<Question> getQuestions() {
		List<Question> questions = new ArrayList<>();
		Random random = new Random();

		for (int i = 1; i <= 20; i++) {
			Question question = new Question();
			question.setId((long) i);
			question.setQuestion("Question " + i);
			question.setEncodedFile("Encoded file " + i);

			List<Answer> answers = new ArrayList<>();
			List<Answer> correctAnswers = new ArrayList<>();

			int totalAnswers = 5 + random.nextInt(3); // Random number between 5 and 7
			int totalCorrectAnswers = 1 + random.nextInt(3); // Random number between 1 and 3

			for (int j = 1; j <= totalAnswers; j++) {
				Answer answer = new Answer();
				if (j <= totalCorrectAnswers) {
					answer.setAnswer("Correct answer " + j + " for question " + i);
					correctAnswers.add(answer);
				} else {
					answer.setAnswer("Incorrect answer " + j + " for question " + i);
				}
				answers.add(answer);
			}

			question.setAnswers(answers);
			question.setCorrectAnswers(correctAnswers);

			questions.add(question);
		}
		return questions ;
	}

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
