package org.studia.javapro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.studia.javapro.Request.TestRequest;
import org.studia.javapro.services.TestService;

@Controller
public class TestController {
	@Autowired
	TestService testService;
	@PostMapping("/test")
	public ResponseEntity createTest(@RequestBody TestRequest testRequest) {
		testService.createTest(testRequest.testUser, testRequest.HowManyQuestions);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/tests")
	public ResponseEntity getTests() {
		return ResponseEntity.ok(testService.getAllTests());
	}
	@GetMapping("/test")
	public ResponseEntity getTest(@RequestBody Long id) {
		return ResponseEntity.ok(testService.getTest(id));
	}
	@PutMapping("/test")
	public ResponseEntity updateTest(@RequestBody TestRequest testRequest) {
		testService.updateTest(testRequest.testUser, testRequest.HowManyQuestions);
		return ResponseEntity.ok().build();
	}
}
