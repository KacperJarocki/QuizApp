package org.studia.javapro.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
public class Client {
	@Column( nullable=false)
	String firstName;
	@Column( nullable=false)
	String lastName;
	@Column(nullable=false)
	Roles role;
	@Column(unique=true, nullable=false)
	String email;
	@Column(nullable=false)
	String password;
	@Column(unique=true, nullable=false)
	String albumNumber;
	@Id
	@GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
	private Long id;
	public Client() {}
	public Client(String firstName, String lastName, Roles role, String email, String password, String albumNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.password = password;
		this.albumNumber = albumNumber;
	}
}