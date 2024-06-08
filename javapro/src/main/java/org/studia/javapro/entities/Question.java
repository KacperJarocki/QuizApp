package org.studia.javapro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
public class Question {
	@Id
	@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
	private Long id;
	@Column(nullable=false, unique=true)
	private String question;
	@ManyToMany
	private List<Answer> answers;
	@OneToMany
	private List<Answer> correctAnswers;

}
