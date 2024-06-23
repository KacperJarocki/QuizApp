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
}
