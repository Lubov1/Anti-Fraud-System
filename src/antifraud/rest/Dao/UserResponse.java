package antifraud.rest.Dao;

import antifraud.security.Dao.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class UserResponse {
    String name;
    String username;

    Long id;
    public UserResponse(User user){
        name = user.getName();
        username = user.getUsername();
        id = user.getId();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
