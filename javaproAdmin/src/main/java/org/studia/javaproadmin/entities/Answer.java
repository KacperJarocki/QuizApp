package org.studia.javaproadmin.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Setter
@Getter
public class Answer {
	private String answer;
	private Long id;
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Answer answer1 = (Answer) o;
		return Objects.equals(answer, answer1.answer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(answer);
	}
}
