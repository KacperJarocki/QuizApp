package org.studia.javapro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studia.javapro.entities.Answer;
import org.studia.javapro.repositories.AnswerRepository;

@Service
public class AnswerService {
	@Autowired
	AnswerRepository answerRepository;
	public void createAnswer(Answer answer) {
		Answer savedAnswer = answerRepository.findByAnswer(answer.getAnswer()).orElse(null);
		if (savedAnswer == null) {
			answerRepository.save(answer);
		} else {
			answer.setId(savedAnswer.getId());
		}
	}
}
