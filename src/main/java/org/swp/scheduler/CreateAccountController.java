package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;

public class CreateAccountController extends WindowController{

    @FXML
    private ChoiceBox<?> accountType;
  
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button createAccountButton;

    @FXML
    void createAccount() {
      
      String username = usernameField.getText();
      String password1 = passwordField1.getText();
      String password2 = passwordField2.getText();
      
      
      if(password1.equals(password2)){
        LoginData data = new LoginData(username, password1, LoginData.AuthType.ADMIN);

        try {
          DatabaseManager.getInstance().storeSingle(data);
          errorMessage("Success", "Account Created!");
          closeWindow(createAccountButton);
        } catch (Exception e) {
          errorMessage("Duplicate User", "An account associated with this username already exists.");
        }
      }
      else {
    	  errorMessage("Incorrect Passwords", "The two passwords do not match.");
      }
    }

}
