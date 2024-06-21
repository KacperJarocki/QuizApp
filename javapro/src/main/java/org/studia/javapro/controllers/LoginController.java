package org.studia.javapro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.studia.javapro.entities.Client;
import org.studia.javapro.entities.Roles;
import org.studia.javapro.services.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;

	@PutMapping("/login")
	public ResponseEntity login(@RequestBody Client client) {
		Roles role = loginService.logIn(client);
		if (role == Roles.NONE){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}else {
			return ResponseEntity.ok(role);
		}
	}
}
