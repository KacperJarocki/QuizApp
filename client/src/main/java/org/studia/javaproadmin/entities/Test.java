package org.studia.javaproadmin.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Test {
	long Id;
	Client testUser;
	List<Question> questions;
	List<GivenAnswer> givenAnswers;
	int score;
	Boolean HasStarted;
}
