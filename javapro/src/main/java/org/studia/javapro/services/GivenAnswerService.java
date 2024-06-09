package org.studia.javapro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studia.javapro.entities.Answer;
import org.studia.javapro.entities.GivenAnswer;
import org.studia.javapro.repositories.GivenAnswerRepository;

import java.util.List;

@Service
public class GivenAnswerService {
	@Autowired
	AnswerService answerService;
	@Autowired
	QuestionService questionService;
	@Autowired
	GivenAnswerRepository givenAnswerRepository;

	public void setAnswerId(Answer answer) {
		answerService.createAnswer(answer);
	}
	public void setAnswerIdForList(List<Answer> answers) {
		for (Answer answer : answers) {
			setAnswerId(answer);
		}
	}
	public void setIdForGivenAnswers(List<GivenAnswer> givenAnswers) {
		for (GivenAnswer givenAnswer : givenAnswers) {
			setAnswerIdForList(givenAnswer.getAnswers());
			questionService.setQuestionId(givenAnswer.getQuestion());
		}
	}
	public GivenAnswer createGivenAnswer(GivenAnswer givenAnswer) {
		setAnswerIdForList(givenAnswer.getAnswers());
		questionService.setQuestionId(givenAnswer.getQuestion());
		givenAnswer = givenAnswerRepository.save(givenAnswer);
		return givenAnswer;
	}
	public void createGivenAnswer(List<GivenAnswer> givenAnswers) {
		for (GivenAnswer givenAnswer : givenAnswers) {
			givenAnswer = createGivenAnswer(givenAnswer);
		}
	}

}

