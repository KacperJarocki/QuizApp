package org.studia.javapro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.studia.javapro.entities.Client;
import org.studia.javapro.services.ClientService;

@Controller
public class ClientController {
	@Autowired
	ClientService clientService;
	@PutMapping("/client/create")
	public ResponseEntity createClient(@RequestBody Client client) {
		clientService.createClient(client);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/client/getAll")
	public ResponseEntity getClients() {
		return ResponseEntity.ok(clientService.getAllClients());
	}
	@GetMapping("/client/get")
	public ResponseEntity getClient(@RequestBody Long id) {
		return ResponseEntity.ok(clientService.getClient(id));
	}
	@PutMapping("/client/update")
	public ResponseEntity updateClient(@RequestBody Client client) {
		clientService.updateClient(client.getId(), client.getFirstName(), client.getLastName(), client.getEmail(), client.getPassword(), client.getAlbumNumber(), client.getRole());
		return ResponseEntity.ok().build();
	}

}
