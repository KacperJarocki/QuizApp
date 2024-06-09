package org.studia.javapro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studia.javapro.entities.*;
import org.studia.javapro.repositories.ClientRepository;
import org.studia.javapro.repositories.TestRepository;

import java.util.*;

@Service
public class TestService {
	@Autowired
	TestRepository testRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	QuestionService questionService;
	@Autowired
	GivenAnswerService givenAnswerService;
	public long createTest(Client client,int howManyQuestions) {
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
		test.setHasStarted(false);
		test = testRepository.save(test);
		return test.getId();
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

	public Test startTest(Long testId) {
		Test test = testRepository.findById(testId).get();
		test.setHasStarted(true);
		testRepository.save(test);
		return test;
	}

	public int finishTest(long testId, List<GivenAnswer> givenAnswers) {
		Test test = testRepository.findById(testId).get();
		System.out.println("jestem przed serwisem");
		givenAnswerService.createGivenAnswer(givenAnswers);
		System.out.println("jestem po serwisie");
		test.setGivenAnswers(givenAnswers);
		int score = calculateScore(test);
		System.out.println("score: "+score);
		test.setScore(score);
		System.out.println(test);
		testRepository.save(test);
		return score;
	}
public int calculateScore(Test test){
    int score = 0;
    HashMap<Long, Question> BaseQuestionFromTest= new HashMap<>();
    HashMap<Long, GivenAnswer> answersGivenByClient= new HashMap<>();
    for(Question question: test.getQuestions()){
        BaseQuestionFromTest.put(question.getId(), question);
    }
    for(GivenAnswer givenAnswer: test.getGivenAnswers()){
        answersGivenByClient.put(givenAnswer.getQuestion().getId(), givenAnswer);
    }
    for(Map.Entry<Long, Question> entry : BaseQuestionFromTest.entrySet()){
        Long questionId = entry.getKey();
        Question baseQuestion = entry.getValue();
        GivenAnswer clientAnswer = answersGivenByClient.get(questionId);
        if(clientAnswer != null){
            Set<Answer> correctAnswersSet = new HashSet<>(baseQuestion.getCorrectAnswers());
            Set<Answer> clientAnswersSet = new HashSet<>(clientAnswer.getAnswers());
            if(correctAnswersSet.equals(clientAnswersSet)){
                score++;
            }
        }
    }
    return score;
}
}

