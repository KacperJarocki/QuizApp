package org.studia.javaproadmin.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.studia.javaproadmin.entities.Mark;
import org.studia.javaproadmin.entities.Test;
import org.studia.javaproadmin.services.InternetService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class ShowMarksViewController {
	MainController mainController;
	InternetService internetService;
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
			tests = internetService.getTests();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		List<Mark> marks = Mark.convertToMarks(tests);
		for (Mark mark : marks) {
			System.out.println(mark);
		}

		albumNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getAlbumNumber());
		testIdColumn.setCellValueFactory(cellData -> cellData.getValue().getTestId());
		pointsColumn.setCellValueFactory(cellData -> cellData.getValue().getPoints());
		gradeColumn.setCellValueFactory(cellData -> cellData.getValue().getGrade());
		passedColumn.setCellValueFactory(cellData ->cellData.getValue().getPassed());

		marksTable.getItems().setAll(marks);
	}
	void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	void setInternetService(InternetService internetService) {
		this.internetService = internetService;
	}



	public void goBack(ActionEvent actionEvent) {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("adminLogged.fxml"));
		try {
			mainController.setNewPane(loader.load());
			AdminLoggedController adminLoggedController = loader.getController();
			adminLoggedController.setMainController(mainController);
			adminLoggedController.setInternetService(internetService);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
