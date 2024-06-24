package org.studia.javaproadmin.Request;

import lombok.Getter;
import lombok.Setter;
import org.studia.javaproadmin.entities.GivenAnswer;

import java.util.List;
@Getter
@Setter
public class FinishedTestRequest {
	long testId;
	List<GivenAnswer> givenAnswers;
	public void printInfo() {
		System.out.println("Test ID: " + testId);
		System.out.println("Given Answers: ");
		for (GivenAnswer givenAnswer : givenAnswers) {
			System.out.println(givenAnswer.toString());
		}
	}
}
