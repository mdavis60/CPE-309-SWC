package org.swp.scheduler;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.shape.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.*;
import org.swp.scheduler.database.models.*;

import javafx.collections.transformation.*;
import javafx.util.Callback;
import javafx.fxml.FXMLLoader;

@SuppressWarnings("restriction")
public class SectionComponentController extends AnchorPane {
	
	private static ObservableList<String> times = FXCollections.observableArrayList("7 am","8 am","9 am","10 am", "11 am", "12 pm", 
			"1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm");
	private Course course; 

	@FXML 
	private ComboBox<String> sectionType;

	@FXML 
	private ComboBox<Teacher> professor;

	@FXML 
	private ComboBox<Room> room;

	@FXML 
	private ComboBox<String> startTime;

	@FXML 
	private ComboBox<String> endTime;

	public SectionComponentController(Course c)
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"/fxml/SectionComponentTemplate.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		this.course = c; 
		
	}

	@FXML
	public void initialize() {
		professor.setCellFactory(new Callback<ListView<Teacher>, ListCell<Teacher>>() {

			@Override
			public ListCell<Teacher> call(ListView<Teacher> param) {

				ListCell<Teacher> cell = new ListCell<Teacher>() {
					@Override
					protected void updateItem(Teacher t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getTeacherName());
						}
					}
				};	

				return cell;
			}
		});

		ObservableList<Teacher> teachers = MasterController.getTeacherData();
		professor.getItems().addAll(teachers);	
		
		room.setCellFactory(new Callback<ListView<Room>, ListCell<Room>>() {

			@Override
			public ListCell<Room> call(ListView<Room> param) {

				ListCell<Room> cell = new ListCell<Room>() {

					@Override
					protected void updateItem(Room t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getRoom());
						}
					}
				};	

				return cell;
			}
		});

		ObservableList<Room> rooms = MasterController.getRoomData();
		room.getItems().addAll(rooms);
		
		startTime.setItems(times);
		endTime.setItems(times);
		sectionType.getItems().add("Lecture");
		sectionType.getItems().add("Lab");
		
	}
	
	
	public Section getSection() {
		Section section = new Section();
		CourseComponent courseComponent = new CourseComponent();
		courseComponent.type = sectionType.getValue();
		section.courseComponent = courseComponent;
		section.room = room.getValue();
		section.teacher = professor.getValue();
		section.startTime = startTime.getValue();
		section.endTime = endTime.getValue();
		
		return section; 
	}
	@FXML
    void onRemove() {
    	((VBox)this.getParent()).getChildren().remove(this);
    }
}
