package com.example.spring2.resources;

import com.example.spring2.Constants;
import com.example.spring2.Exceptions.EtAuthException;
import com.example.spring2.domain.User;
import com.example.spring2.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) throws EtAuthException {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestap = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestap))
                .setExpiration(new Date(timestap + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>>  resgisterUser(@RequestBody Map<String, Object> userMap) throws EtAuthException {
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String password = (String) userMap.get("password");
        String email = (String) userMap.get("email");
        User user = userService.registerUser(firstName, lastName, email, password);
        Map<String, String> map = user.getHashMapUser();

        userMap.put("message", "registered succesfully");
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }
}
