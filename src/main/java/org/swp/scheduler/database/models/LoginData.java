package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/6/17.
 *
 */
@Entity
@Table(name = "LoginData")
public class LoginData extends Model {
  public enum AuthType {
    ADMIN, DSA, STUDENT;
  }

  @Id
  public String username;
  public String password;
  public AuthType type;

  public LoginData() {

  }

  public LoginData(String username, String password, AuthType type) {
    this.username = username;
    this.password = password;
    this.type = type;
  }

  public static AuthType getAuthType(String type) {
    type.toLowerCase().trim();
    switch (type) {
      case "admin":
        return AuthType.ADMIN;
      case "dsa":
        return AuthType.DSA;
      case "student":
        return AuthType.STUDENT;
      default:
        return AuthType.ADMIN;
    }
  }
}
