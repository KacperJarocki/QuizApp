package org.studia.javaproadmin.entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Roles {
	ADMIN,
	TEACHER,
	STUDENT,
	NONE;

	public static ObservableList<Roles> getRoles() {
		return FXCollections.observableArrayList(Roles.values());
	}
}
