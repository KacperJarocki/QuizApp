package org.studia.javapro.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.studia.javapro.entities.Client;
import org.studia.javapro.entities.Roles;
import org.studia.javapro.services.ClientService;

@Configuration
public class DatabaseInitializer {
	@Bean
	public CommandLineRunner initDatabase(ClientService clientService) {
		return args -> {
			Client adminClient = new Client();
			adminClient.setFirstName("Admin");
			adminClient.setLastName("Admin");
			adminClient.setEmail("admin@example.com");
			adminClient.setPassword("password"); // Consider using password encoder
			adminClient.setAlbumNumber("admin");
			adminClient.setRole(Roles.ADMIN);

			Client teacherClient = new Client();
			teacherClient.setFirstName("Teacher");
			teacherClient.setLastName("Teacher");
			teacherClient.setEmail("teacher@example.com");
			teacherClient.setPassword("password"); // Consider using password encoder
			teacherClient.setAlbumNumber("teacher");
			teacherClient.setRole(Roles.TEACHER);

			Client userClient = new Client();
			userClient.setFirstName("User");
			userClient.setLastName("User");
			userClient.setEmail("user@example.com");
			userClient.setPassword("password"); // Consider using password encoder
			userClient.setAlbumNumber("169551");
			userClient.setRole(Roles.STUDENT);

			clientService.createClient(adminClient);
			clientService.createClient(teacherClient);
			clientService.createClient(userClient);
		};
	}
}
