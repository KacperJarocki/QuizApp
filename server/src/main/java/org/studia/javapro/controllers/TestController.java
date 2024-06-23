package org.studia.javapro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.studia.javapro.Request.FinishedTestRequest;
import org.studia.javapro.Request.TestRequest;
import org.studia.javapro.entities.Test;
import org.studia.javapro.services.TestService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {
	@Autowired
	TestService testService;
	@PostMapping("/test")
	public ResponseEntity createTest(@RequestBody TestRequest testRequest) {
		long testId = testService.createTest(testRequest.testUser, testRequest.HowManyQuestions);
		Map<String, Long> responseBody = new HashMap<>();
		responseBody.put("testId", testId);
		return ResponseEntity.ok(responseBody);
	}
	@GetMapping("/startTest")
	public ResponseEntity startTest(@RequestParam long id) {
		Test test = testService.startTest(id);
		return ResponseEntity.ok(test);
	}
	@PutMapping("/finishTest")
	public ResponseEntity finishTest(@RequestBody FinishedTestRequest finishedTestRequest) {
		System.out.println("siema controler");
		int score = testService.finishTest(finishedTestRequest.getTestId(), finishedTestRequest.getGivenAnswers());
		Map<String, Integer> responseBody = new HashMap<>();
		responseBody.put("score", score);
		return ResponseEntity.ok(responseBody);
	}
	@GetMapping("/tests")
	public ResponseEntity getTests() {
		return ResponseEntity.ok(testService.getAllTests());
	}
	@GetMapping("/test")
	public ResponseEntity getTest(@RequestParam long id) {
		return ResponseEntity.ok(testService.getTest(id));
	}
	@PutMapping("/test")
	public ResponseEntity updateTest(@RequestBody TestRequest testRequest) {
		testService.updateTest(testRequest.testUser, testRequest.HowManyQuestions);
		return ResponseEntity.ok().build();
	}
}
