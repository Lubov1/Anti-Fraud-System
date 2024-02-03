package antifraud.rest.Dao;

import antifraud.security.Dao.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class UserRequest {
    String name;
    String username;
    String password;
    UserRequest(User user) {
        name = user.getName();
        username = user.getUsername();
        password = user.getPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
