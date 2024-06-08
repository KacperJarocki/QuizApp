package org.studia.javapro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studia.javapro.entities.Client;
import org.studia.javapro.entities.Question;
import org.studia.javapro.entities.Test;
import org.studia.javapro.repositories.ClientRepository;
import org.studia.javapro.repositories.TestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TestService {
	@Autowired
	TestRepository testRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	QuestionService questionService;
	public void createTest(Client client,int howManyQuestions) {
		Test test = new Test();
		Client clientSearch= clientRepository.findByAlbumNumber(client.getAlbumNumber()).orElse(null);
		if(clientSearch!=null) {
			test.setTestUser(clientSearch);
		}else {
			System.out.println("Client not found");
			//throw new Exception("Client not found");
		}
		Random random = new Random(client.getAlbumNumber().hashCode());
		test.setQuestions(getQuestions(howManyQuestions,random));
		testRepository.save(test);
	}
	private List<Question> getQuestions(int howManyQuestions,Random random){
		List<Question> questions = questionService.getAllQuestions();
		List<Question> questionsToReturn = new ArrayList<>();
		for(int i=0;i<howManyQuestions;i++) {
			int randomNumber = 0 + random.nextInt(questions.size());
			questions.get(randomNumber);
			questionsToReturn.add(questions.get(randomNumber));
			questions.remove(randomNumber);
		}
		return questionsToReturn;
	}
	public Test getTest(Long id) {
		return testRepository.findById(id).get();
	}
	public List<Test> getAllTests() {
		return testRepository.findAll();
	}

	public void updateTest(Client testUser, int howManyQuestions) {
	}
}

