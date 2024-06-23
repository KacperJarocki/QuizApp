package org.studia.javapro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studia.javapro.entities.Client;
import org.studia.javapro.entities.Roles;
import org.studia.javapro.repositories.ClientRepository;


@Service
public class LoginService {
	@Autowired
	ClientRepository clientRepository;
	public Roles logIn(Client client) {
		Client clientFromDataBase = clientRepository.findByAlbumNumber(client.getAlbumNumber()).orElse(null);
		if (clientFromDataBase == null) {
			return Roles.NONE;
		}else if (clientFromDataBase.getPassword().equals(client.getPassword())) {
			return clientFromDataBase.getRole();
		}else {
			return Roles.NONE;
		}
	}
}
