package org.studia.javapro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studia.javapro.entities.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
Optional<Client> findByAlbumNumber(String albumNumber);
}
