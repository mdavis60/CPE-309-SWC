package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;

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
        String password = passwordField.getText();

        try {
          LoginData retrievedData = (LoginData) DatabaseManager.getInstance().getSingle(LoginData.class, username);
          
          if(retrievedData.password.equals(password)){
            openWindow("Scheduler");
            closeWindow(signInButton);
          } else {
            System.out.println("Incorrect password");
          }
          
        } catch (Exception e) {
          System.out.println("No username in database");
        }
    }
}