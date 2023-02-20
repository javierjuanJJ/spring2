package com.example.spring2.resources;

import com.example.spring2.Exceptions.EtAuthException;
import com.example.spring2.domain.User;
import com.example.spring2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>>  resgisterUser(@RequestBody Map<String, Object> userMap) throws EtAuthException {
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String password = (String) userMap.get("password");
        String email = (String) userMap.get("email");
        User user = userService.registerUser(firstName, lastName, email, password);
        Map<String, String> map = new HashMap<>();
        userMap.put("message", "registered succesfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
