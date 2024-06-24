package org.studia.javaproadmin.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.studia.javaproadmin.Request.FinishedTestRequest;
import org.studia.javaproadmin.entities.Answer;
import org.studia.javaproadmin.entities.GivenAnswer;
import org.studia.javaproadmin.entities.Question;
import org.studia.javaproadmin.entities.Test;
import org.studia.javaproadmin.services.InternetService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TestController {
	MainController mainController;
	InternetService internetService;
	Test test;
	List<Answer> selectedAnswers = new ArrayList<>();
	FinishedTestRequest finishedTestRequest = new FinishedTestRequest();
	int currentQuestion = -1;
	FXMLLoader loader;
	String clientAlbumNumber;
	@FXML
	private ListView<String> answerList;
	@FXML
	private ImageView photo;
	@FXML
	private Label question;
	@FXML
	private Label questionNumber;
	@FXML
	private Button nextQuestionButton;
	@FXML
	public void initialize(){
		answerList.setCellFactory(new Callback<>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<>() {
					private final CheckBox checkBox = new CheckBox();
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty || item == null) {
							setGraphic(null);
						} else {
							checkBox.setText(item);
							checkBox.setSelected(false);
							checkBox.setOnAction(e -> {
								if (checkBox.isSelected()) {
									Answer answer = new Answer();
									answer.setAnswer(item);
									selectedAnswers.add(answer);
								} else {
									selectedAnswers.removeIf(answer -> answer.getAnswer().equals(item));
								}
							});
							setGraphic(checkBox);
						}
					}
				};
			}
		});
	}
	@FXML
	void nextQuestion(ActionEvent event) {
		if(currentQuestion>-1){
			GivenAnswer givenAnswer = new GivenAnswer();
			givenAnswer.setQuestion(test.getQuestions().get(currentQuestion));
			givenAnswer.setAnswers(getSelectedAnswers());
			if(finishedTestRequest.getGivenAnswers()==null){
				finishedTestRequest.setGivenAnswers(new ArrayList<>());
			}
			finishedTestRequest.getGivenAnswers().add(givenAnswer);
			selectedAnswers.clear();
			answerList.getItems().clear();
		}
		currentQuestion++;
		if (currentQuestion == test.getQuestions().size() -1 ) {
			nextQuestionButton.setText("Finish");
			updateUI();
		} else if (currentQuestion == test.getQuestions().size()) {
			int score = 0;
			try {
				finishedTestRequest.printInfo();
				finishedTestRequest.setTestId(test.getId());
				score = internetService.finishTest(finishedTestRequest);
			} catch (IOException e) {
				System.out.println("Error in finishTest");
				throw new RuntimeException(e);
			}
			Pane pane = null;
			loader = new FXMLLoader(this.getClass().getResource("testScore.fxml"));
			try {
				pane = loader.load();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			TestScoreController testScoreController = loader.getController();
			testScoreController.setMainController(mainController);
			testScoreController.setInternetService(internetService);
			testScoreController.setScore(score);
			testScoreController.setClientAlbumNumber(clientAlbumNumber);
			mainController.setNewPane(pane);

		}else updateUI();
	}
	private void updateUI() {
		Question questionFromTest = test.getQuestions().get(currentQuestion);
		question.setText(questionFromTest.getQuestion());
		questionNumber.setText("Question " + (currentQuestion + 1));
		byte[] imageBytes = Base64.getDecoder().decode(questionFromTest.getEncodedFile());
		Image image = new Image(new ByteArrayInputStream(imageBytes));
		photo.setImage(image);
		answerList.setItems(createObservableList(questionFromTest.getAnswers()));
	}

	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
	void setTest(Test test) {
		this.test = test;
	}
	public List<Answer> getSelectedAnswers(){
		return new ArrayList<>(selectedAnswers);
	}

	private ObservableList<String> createObservableList(List<Answer> answers) {
		ObservableList<String> observableList = FXCollections.observableArrayList();
		for (Answer answer : answers) {
			observableList.add(answer.getAnswer());
		}
		return observableList;
	}
	public void setClientAlbumNumber(String clientAlbumNumber){
		this.clientAlbumNumber = clientAlbumNumber;
	}
}
