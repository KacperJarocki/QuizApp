package org.studia.javaproadmin.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Test {
	long id;
	Client testUser;
	List<Question> questions;
	List<GivenAnswer> givenAnswers;
	int score;
	Boolean HasStarted;
	public Test createTestWithPopulatedQuestions(Client client) {
		Test test = new Test();
		test.setTestUser(client);
		test.setQuestions(Question.getQuestions());
		return test;
	}
	public void printTestInfo(){
		System.out.println("Test id: " + id);
		System.out.println("Test user: " + testUser);
		System.out.println("score: " + score);
	}
}
