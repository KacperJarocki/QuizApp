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
		return points >= 50 ? "Passed" : "Failed";
	}

	private String calculateGrade(int points) {
		if(points >= 90) {
			return "5";
		} else if(points >= 80) {
			return "4,5";
		} else if(points >= 70) {
			return "4";
		} else if(points >= 60) {
			return "3,5";
		} else {
			return "2";
		}
	}

	public List<Mark> convertToMarks(List<Test> tests){
		List<Mark> marks = new ArrayList<>();
		for(Test test : tests) {
			marks.add(new Mark(test.getTestUser().albumNumber,String.valueOf(test.getId()), test.getScore()));
		}
		return marks;
	}

}
