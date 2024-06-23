package org.studia.javaproadmin.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
	String firstName;
	String lastName;
	Roles role;
	String email;
	String password;
	String albumNumber;
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