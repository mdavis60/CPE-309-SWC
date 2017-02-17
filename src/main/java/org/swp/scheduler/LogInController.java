package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LogInController extends WindowController{

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML private Button signInButton;
    
    @FXML
    void enterPressed(KeyEvent key) throws Exception{
    	if(key.getCode() == KeyCode.ENTER){
    		validateInfo();
    	}
    }
    
    //function to process user credentials
    public void validateInfo() throws Exception {
    	
        String username = usernameField.getText();
        String password = passwordField.getText();

        openWindow("Scheduler");
        closeWindow(signInButton);

        /*
        try {
          LoginData retrievedData = (LoginData) DatabaseManager.getInstance().getSingle(LoginData.class, username);
          
          
          if(retrievedData.password.equals(password)){
            openWindow("Scheduler");
            closeWindow(signInButton);
          } else {
             errorMessage("Bad Password", "Please re-enter your password.");
          }
          
        } catch (Exception e) {
<<<<<<< Updated upstream
        	errorMessage("Invalid Credentials", "There is no account associated with this user.");
        } */
    }
}
