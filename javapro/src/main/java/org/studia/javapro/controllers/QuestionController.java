package org.studia.javapro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.studia.javapro.entities.Question;
import org.studia.javapro.services.AnswerService;
import org.studia.javapro.services.QuestionService;

@Controller
public class QuestionController {
	@Autowired
	QuestionService questionServices;
	@Autowired
	AnswerService answerService;

	@PostMapping("/question")
	public ResponseEntity createQuestion(@RequestBody Question question) {
		question.getAnswers().forEach(answer -> answerService.createAnswer(answer) );
		question.getCorrectAnswers().forEach(answer -> answerService.createAnswer(answer) );
		questionServices.createQuestion(question);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/questions")
	public ResponseEntity getQuestions() {
		return ResponseEntity.ok(questionServices.getAllQuestions());
	}
	@GetMapping("/question")
	public ResponseEntity getQuestion(@RequestBody Long id) {
		return ResponseEntity.ok(questionServices.getQuestion(id));
	}
	@PutMapping("/question")
	public ResponseEntity updateQuestion(@RequestBody Question question) {
		question.getAnswers().forEach(answer -> answerService.createAnswer(answer) );
		question.getCorrectAnswers().forEach(answer -> answerService.createAnswer(answer) );
		questionServices.updateQuestion(question);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/question")
	public ResponseEntity deleteQuestion(@RequestBody Long id) {
		questionServices.deleteQuestion(id);
		return ResponseEntity.ok().build();
	}
}
