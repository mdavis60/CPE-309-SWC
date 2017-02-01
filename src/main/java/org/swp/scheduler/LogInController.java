package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.Button;

import org.apache.commons.lang.StringUtils;


public class LogInController
{

    @FXML private TextField usernameField;
    @FXML private TextField passwordField;

    //function to process user credentials
    public void validateInfo() {
    	
        String username = usernameField.getText();

        usernameField.setText("test");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sign-In");
        alert.setHeaderText("Credentials Validated");
        String s ="Thanks for signing in! You will now be redirected.";
        alert.setContentText(s);
        alert.show();
       
    }
    
    
  

}
