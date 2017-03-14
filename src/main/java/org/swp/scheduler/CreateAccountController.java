package org.swp.scheduler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;
import org.swp.scheduler.database.models.Teacher;

/**
 * Allows DSA Users to create different types of accounts on the system.
 *
 */
@SuppressWarnings("restriction")
public class CreateAccountController extends WindowController {

  @FXML
  private ChoiceBox<String> accountType;

  @FXML
  private TextField firstNameField;

  @FXML
  private TextField lastNameField;

  @FXML
  private Label workUnitLabel;

  @FXML
  private TextField workUnitField;

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField1;

  @FXML
  private PasswordField passwordField2;

  @FXML
  private Button createAccountButton;

  private String firstName;
  private String lastName;
  private String username;
  private String password1;
  private String password2;
  private String account;

  @FXML
  public void initialize() {
    accountType.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        account = accountType.getValue();
        if (account.equals("Faculty")) {
          workUnitLabel.setPrefHeight(17);
          workUnitField.setPrefHeight(25);
        } else {
          workUnitLabel.setPrefHeight(0);
          workUnitField.setPrefHeight(0);
        }
      }
    });
  }

  @FXML
  void createAccount() {

    firstName = firstNameField.getText();
    lastName = lastNameField.getText();
    username = usernameField.getText();
    password1 = passwordField1.getText();
    password2 = passwordField2.getText();

    System.out.println("Account: " + account);

    if (password1.equals(password2)) {
      LoginData data = new LoginData(username, password1,
          LoginData.getAuthType(account));
      if (account.equals("Faculty")) {
        Teacher teacher = new Teacher(username, lastName,
            Integer.parseInt(workUnitField.getText()));
        MasterController.getInstance().addToTeachers(teacher);
      }
      try {
        DatabaseManager.getInstance().storeSingle(data);
        errorMessage("Success", "Account Created!");
        closeWindow(createAccountButton);
      } catch (Exception ex) {
        errorMessage("Duplicate User",
            "An account associated with this username already exists.");
      }
    } else {
      errorMessage("Incorrect Passwords", "The two passwords do not match.");
    }
  }
}
