package org.studia.javapro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studia.javapro.entities.Question;
import org.studia.javapro.repositories.QuestionRepository;

import java.util.List;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionsRepository;
	public void createQuestion(Question question) {
		questionsRepository.save(question);
	}
	public void deleteQuestion(long id) {
		questionsRepository.deleteById(id);
	}
	public void updateQuestion(Question	question) {
		questionsRepository.save(question);
	}
	public Question getQuestion(Long id) {
		return questionsRepository.findById(id).get();
	}
	public List<Question> getAllQuestions() {
		return questionsRepository.findAll();
	}

}
