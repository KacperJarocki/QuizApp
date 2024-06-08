package org.studia.javapro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studia.javapro.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
}
