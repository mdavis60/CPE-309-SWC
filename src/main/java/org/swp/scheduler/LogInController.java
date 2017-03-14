package org.swp.scheduler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.swp.scheduler.database.DatabaseManager;
import org.swp.scheduler.database.models.LoginData;

/**
 * Login controller authenticates users and saves their authentication level in
 * the global state so that each action can be cross checked with their permissions
 *
 *
 * SRS: Section 5.3, 4.4.1
 * SDS: Section 2.2
 */
public class LogInController extends WindowController {

  @FXML
  private TextField usernameField;
  @FXML
  private PasswordField passwordField;

  @FXML
  private Button signInButton;

  @FXML
  void enterPressed(KeyEvent key) throws Exception {
    if (key.getCode() == KeyCode.ENTER) {
      validateInfo();
    }
  }

  // function to process user credentials
  public void validateInfo() throws Exception {

    String username = usernameField.getText();
    String password = passwordField.getText();

    // openWindow("Scheduler");
    // closeWindow(signInButton);

    try {
      LoginData retrievedData = (LoginData) DatabaseManager.getInstance()
          .getSingle(LoginData.class, username);

      if (retrievedData.password.equals(password)) {
        openWindow("Scheduler");
        closeWindow(signInButton);
      } else {
        errorMessage("Bad Password", "Please re-enter your password.");
      }

    } catch (Exception e) {
      errorMessage("Invalid Credentials",
          "There is no account associated with this user.");
    }
  }
}
