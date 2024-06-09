package org.studia.javapro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class GivenAnswer {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="CUST_SEQ")
	long id;
	@ManyToOne
	Question question;
	@ManyToMany
	List<Answer> answers;
}
