package org.studia.javapro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Setter
@Getter
public class Answer {
	private String answer;
	@Id
	@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
	private Long id;
}
