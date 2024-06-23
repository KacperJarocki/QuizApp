package org.studia.javaproadmin.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class GivenAnswer {
	long id;
	Question question;
	List<Answer> answers;

}
