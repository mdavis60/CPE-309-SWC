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
    private TextField emailField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button createAccountButton;

    @FXML
    void createAccount() {
      
      String username = usernameField.getText();
      String email = emailField.getText();
      String password1 = passwordField1.getText();
      String password2 = passwordField2.getText();
      
      if(password1.equals(password2)){
        LoginData data = new LoginData(username, email, password1, LoginData.AuthType.ADMIN);

        try {
          DatabaseManager.getInstance().storeSingle(data);
          closeWindow(createAccountButton);
        } catch (Exception e) {
          System.out.println("Username already in database");
        }
      }
    }

}
