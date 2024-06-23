package org.studia.javapro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studia.javapro.entities.Answer;

import java.util.Optional;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

	Optional<Answer> findByAnswer(String answer);
}
