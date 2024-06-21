package org.studia.javapro.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Setter
@Getter
public class Answer {
	@Column(nullable=false, unique=true)
	private String answer;
	@Id
	@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
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
