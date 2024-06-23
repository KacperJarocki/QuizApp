package org.studia.javaproadmin.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.studia.javaproadmin.Request.FinishedTestRequest;
import org.studia.javaproadmin.entities.Answer;
import org.studia.javaproadmin.entities.GivenAnswer;
import org.studia.javaproadmin.entities.Question;
import org.studia.javaproadmin.entities.Test;
import org.studia.javaproadmin.services.InternetService;

import java.util.ArrayList;
import java.util.List;

public class TestController {
	MainController mainController;
	InternetService internetService;
	Test test;
	List<Answer> selectedAnswers = new ArrayList<>();
	FinishedTestRequest finishedTestRequest = new FinishedTestRequest();
	int currentQuestion = -1;
	@FXML
	private ListView<String> answerList;
	@FXML
	private ImageView photo;
	@FXML
	private Label question;
	@FXML
	private Label questionNumber;
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
			givenAnswer.getQuestion().printQuestionInfo();
			givenAnswer.getAnswers().forEach(answer -> System.out.println("selected anserws" + answer.getAnswer()));
		}
		currentQuestion++;
		Question questionFromTest = test.getQuestions().get(currentQuestion);
		question.setText(questionFromTest.getQuestion());
		questionNumber.setText("Question " + (currentQuestion + 1));
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
}
