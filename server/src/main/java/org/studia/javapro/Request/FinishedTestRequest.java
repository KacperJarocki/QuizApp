package org.studia.javapro.Request;

import lombok.Getter;
import lombok.Setter;
import org.studia.javapro.entities.GivenAnswer;

import java.util.List;
@Getter
@Setter

public class FinishedTestRequest {
	long testId;
	List<GivenAnswer> givenAnswers;

}
