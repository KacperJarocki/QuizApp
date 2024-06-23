package org.studia.javapro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studia.javapro.entities.Client;
import org.studia.javapro.entities.Roles;
import org.studia.javapro.repositories.ClientRepository;

import java.util.List;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;

	public void createClient(final String firstName, final String lastName, final String email, final String password, final String albumNumber, final Roles role) {
		Client client = new Client(firstName, lastName, role, email, password, albumNumber);
		System.out.println("Client created");
		clientRepository.save(client);
	}
	public void createClient(final Client client) {
		System.out.println("Client created");
		clientRepository.save(client);
	}
	public void deleteClient(final Long id) {
		clientRepository.deleteById(id);
	}
	public void updateClient(final Long id, final String firstName, final String lastName, final String email, final String password, final String albumNumber, final Roles role) {
		Client client = clientRepository.findById(id).get();
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setEmail(email);
		client.setPassword(password);
		client.setAlbumNumber(albumNumber);
		client.setRole(role);
		clientRepository.save(client);
	}
	public Client getClient(final Long id) {
		return clientRepository.findById(id).get();
	}
	public void printAllClients() {
		clientRepository.findAll().forEach(System.out::println);
	}
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
}
