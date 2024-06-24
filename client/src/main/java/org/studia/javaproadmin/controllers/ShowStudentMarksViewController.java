package org.studia.javaproadmin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.studia.javaproadmin.entities.Mark;
import org.studia.javaproadmin.entities.Test;
import org.studia.javaproadmin.services.InternetService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class ShowStudentMarksViewController {
	MainController mainController;
	InternetService internetService;
	String clientAlbumNumber;
	Pane pane = null;
	public TableView<Mark> marksTable;
	public TableColumn<Mark, String> albumNumberColumn;
	public TableColumn<Mark, String> testIdColumn;
	public TableColumn<Mark, String> pointsColumn;
	public TableColumn<Mark, String> gradeColumn;
	public TableColumn<Mark, String> passedColumn;

	public void initialize() {
		List<Test> tests = null;
		try {
			internetService = new InternetService();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println(clientAlbumNumber);
			tests = internetService.getUserTests(clientAlbumNumber);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		List<Mark> marks = Mark.convertToMarks(tests);
		for (Mark mark : marks) {
			System.out.println(mark.getGrade());
			System.out.println(mark.getAlbumNumber());
		}

		albumNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getAlbumNumber());
		testIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTestId());
		pointsColumn.setCellValueFactory(cellData -> cellData.getValue().getPoints());
		gradeColumn.setCellValueFactory(cellData -> cellData.getValue().getGrade());
		passedColumn.setCellValueFactory(cellData ->cellData.getValue().getPassed());

		marksTable.getItems().setAll(marks);
	}
	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("studentLogged.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		mainController.setNewPane(pane);
		StudentLoggedController studentLoggedController = loader.getController();
		studentLoggedController.setMainController(mainController);
		studentLoggedController.setInternetService(internetService);
		studentLoggedController.setClientAlbumNumber(clientAlbumNumber);
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	public void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}
	@FXML
	public void setClientAlbumNumber(String clientAlbumNumber) {
		this.clientAlbumNumber = clientAlbumNumber;
	}
}
