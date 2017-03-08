package org.swp.scheduler;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;
import org.swp.scheduler.database.models.Teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;

public class CreateAccountController extends WindowController {

  @FXML
  private ChoiceBox<String> accountType;

  @FXML
  private TextField firstNameField;

  @FXML
  private TextField lastNameField;

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

    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String username = usernameField.getText();
    String password1 = passwordField1.getText();
    String password2 = passwordField2.getText();
    String account = accountType.getValue();
    System.out.println("Account: " + account);

    if (password1.equals(password2)) {
      LoginData data = new LoginData(username, password1,
          LoginData.AuthType.ADMIN);
      Teacher teacher = new Teacher(username, firstName + lastName);
      try {
        DatabaseManager.getInstance().storeSingle(data);
        MasterController.getInstance().addToTeachers(teacher);
        errorMessage("Success", "Account Created!");
        closeWindow(createAccountButton);
      } catch (Exception e) {
        errorMessage("Duplicate User",
            "An account associated with this username already exists.");
      }
    } else {
      errorMessage("Incorrect Passwords", "The two passwords do not match.");
    }
  }
}
