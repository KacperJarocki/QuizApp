package org.studia.javaproadmin.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Question {
	private Long id;
	private String question;
	private List<Answer> answers;
	private List<Answer> correctAnswers;

}
