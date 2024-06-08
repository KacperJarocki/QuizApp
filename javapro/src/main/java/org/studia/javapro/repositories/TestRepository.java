package org.studia.javapro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studia.javapro.entities.Test;
@Repository
public interface TestRepository extends JpaRepository<Test, Long>{
}
