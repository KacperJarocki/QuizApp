package org.studia.javaproadmin.entities;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Mark {
	private SimpleStringProperty albumNumber;
	private SimpleStringProperty testId;
	private SimpleStringProperty points;
	private SimpleStringProperty grade;
	private SimpleStringProperty passed;

	public Mark(String albumNumber, String testId, int points) {
		this.albumNumber = new SimpleStringProperty(albumNumber);
		this.testId = new SimpleStringProperty(testId);
		this.points = new SimpleStringProperty(String.valueOf(points));
		this.grade = new SimpleStringProperty(calculateGrade(points));
		this.passed = new SimpleStringProperty(hasPassed(points));
	}

	private String hasPassed(int points) {
		return (points/20 * 100)>= 50 ? "Passed" : "Failed";
	}

	private String calculateGrade(int score) {
		score = score / 20 * 100;
		if (score >= 95) {
			return "twoja ocena 5.0";
		} else if (score >= 90) {
			return "twoja ocena 4.5";
		} else if (score >= 80) {
			return "twoja ocena 4.0";
		} else if (score >= 70) {
			return "twoja ocena 3.5";
		} else if (score >= 50) {
			return "twoja ocena 3.0";
		} else {
			return "twoja ocena 2.0";
		}
	}

	public static List<Mark> convertToMarks(List<Test> tests){
		List<Mark> marks = new ArrayList<>();
		for(Test test : tests) {
			marks.add(new Mark(test.getTestUser().albumNumber,String.valueOf(test.getId()), test.getScore()));
		}
		return marks;
	}

}
