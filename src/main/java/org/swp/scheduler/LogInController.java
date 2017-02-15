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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Error");
              alert.setHeaderText("Bad Password");
              String s ="Please re-enter your password.";
              alert.setContentText(s);
              alert.show();
          }
          
        } catch (Exception e) {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Error");
          alert.setHeaderText("Invalid Credentials");
          String s ="There is no account associated with this user.";
          alert.setContentText(s);
          alert.show();
        }
    }
}