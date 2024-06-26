package org.studia.javaproadmin.controllers;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.studia.javaproadmin.entities.Answer;
import org.studia.javaproadmin.entities.Question;
import org.studia.javaproadmin.services.InternetService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddQuestionFormController {
	MainController mainController;
	InternetService internetService;
	Pane pane;
	FXMLLoader loader;
	File selectedFile;
	List<Answer> selectedAnswers = new ArrayList<>();

	@FXML
	private TextField Anserws;

	@FXML
	private Pane QuestionForm;

	@FXML
	private Button fileChooser;

	@FXML
	private TextField question;

	@FXML
	private ListView<String> listOfAnserws;
	@FXML
	public void initialize(){
		listOfAnserws.setCellFactory(new Callback<>() {
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
	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {this.internetService = internetService;}
	@FXML
	public void buttonChooseFile(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg","*.pdf")
		);
		selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			this.fileChooser.setText(selectedFile.getName());
		} else {
			this.fileChooser.setText("File selection cancelled.");
		}
	}
	@FXML
	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("adminLogged.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainController.setNewPane(pane);
		AdminLoggedController controller = loader.getController();
		controller.setMainController(mainController);
		controller.setInternetService(internetService);
	}
	@FXML
	public void buttonSaveQuestion(ActionEvent actionEvent) {
		Question question = new Question();
		question.setQuestion(this.question.getText());
		byte[] file;
		if (selectedFile != null) {
			try {
				file = Files.readAllBytes(selectedFile.toPath());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			question.setEncodedFile(Base64.getEncoder().encodeToString(file));
		}
		question.setAnswers(getAnswers());
		question.setCorrectAnswers(getSelectedAnswers());
		selectedAnswers.clear();
		try {
			internetService.sendQuestion(question);
		} catch (IOException e) {
			e.printStackTrace();
		}
		loader = new FXMLLoader(getClass().getResource("adminLogged.fxml"));
		try {
			pane = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdminLoggedController adminLoggedController = loader.getController();
		adminLoggedController.setMainController(mainController);
		adminLoggedController.setInternetService(internetService);
		mainController.setNewPane(pane);
	}
	@FXML
	public void addAnswer(ActionEvent actionEvent) {
		if (listOfAnserws.getItems().isEmpty()){
			listOfAnserws.setItems(FXCollections.observableArrayList(Anserws.getText()));
		}else{
			listOfAnserws.getItems().add(Anserws.getText());
		}
	}
	public List<Answer> getAnswers(){
		List<Answer> answers= new ArrayList<>();
		for (String item : listOfAnserws.getItems()) {
			Answer answer = new Answer();
			answer.setAnswer(item);
			answers.add(answer);
		}
		return answers;
	}
	public List<Answer> getSelectedAnswers(){
		return new ArrayList<>(selectedAnswers);
	}
}
