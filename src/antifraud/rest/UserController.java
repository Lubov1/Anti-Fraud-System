package antifraud.rest;

import antifraud.rest.Dao.UserResponse;
import antifraud.security.Dao.Role;
import antifraud.security.Dao.User;
import antifraud.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/api/auth/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity createUser(@RequestBody User user) {
        try {
            String username = user.getUsername();
            if (userRepository.existsByUsername(username)) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(409));
            }
            if(username.isEmpty() || user.getName().isEmpty() ||
            user.getPassword().isEmpty()) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(400));
            }
            userRepository.save(new User(user.getName(), username,
                    passwordEncoder.encode(user.getPassword()), Role.USER));
            User user1 = userRepository.findUserByUsername(username);
            HashMap<String, Object> e = new HashMap<>();
            e.put("id", user1.getId());
            e.put("name", user1.getName());
            e.put("username", user1.getUsername());
            return new ResponseEntity<>(e, HttpStatusCode.valueOf(201));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping(value = "/api/auth/list")
    private ResponseEntity getUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users == null) {
                return new ResponseEntity<>(new ArrayList<String>(), HttpStatusCode.valueOf(200));
            }
            List<UserResponse> res = users.stream().map(UserResponse::new).toList();
            return new ResponseEntity<>(res, HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatusCode.valueOf(405));
        }
    }

    @DeleteMapping(value = "/api/auth/user/{username}")
    private ResponseEntity deleteUser(@PathVariable String username) {
        if (userRepository.deleteUserByUsername(username) != 1) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        HashMap<String, String> answer = new HashMap<>();
        answer.put("username", username);
        answer.put("status", "Deleted successfully!");
        return new ResponseEntity<>(answer, HttpStatusCode.valueOf(200));
    }
}
