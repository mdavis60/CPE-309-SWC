package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class LogInController extends WindowController{

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML private Button signInButton;
    
    //function to process user credentials
    public void validateInfo() throws Exception {
    	
        String username = usernameField.getText();

        usernameField.setText("test");

        openWindow("Scheduler");

        closeWindow(signInButton);
    }
}