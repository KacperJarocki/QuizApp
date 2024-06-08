package org.studia.javapro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studia.javapro.entities.Question;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
}
