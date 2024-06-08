package org.studia.javapro.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Test {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUST_SEQ")
	long Id;
	@ManyToOne
	Client testUser;
	@ManyToMany
	List<Question> questions;
	int score;
}
