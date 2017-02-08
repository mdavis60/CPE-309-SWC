package org.swp.scheduler.database.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jackson on 2/6/17.
 */
@Entity
@Table(name = "LoginData")
public class LoginData extends Model {
    @Id
    String username;
    String email;
    String password;
    int type;

    public LoginData(String username, String email, String password, int type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
