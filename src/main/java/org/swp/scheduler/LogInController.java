package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;

import java.awt.Panel;
import java.awt.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javafx.scene.control.Alert.AlertType;

import java.awt.Button;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import javafx.scene.layout.Pane;


public class LogInController {

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    Stage prevStage;

    public void setPrevStage(Stage stage){
         this.prevStage = stage;
    }

    //function to process user credentials
    public void validateInfo() throws Exception {
    	
        String username = usernameField.getText();

        usernameField.setText("test");

        String fxmlFile = "/fxml/CreateCourse.fxml";
        Stage stage = new Stage();
        stage.setTitle("Shop Management");
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getResource(fxmlFile));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
      //  prevStage.close();
        stage.show();
   
        
    }
}