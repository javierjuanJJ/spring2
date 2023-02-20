package com.example.spring2.services;

import com.example.spring2.Exceptions.EtAuthException;
import com.example.spring2.domain.User;
import com.example.spring2.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) {
            email = email.toLowerCase();
        }
        if (!pattern.matcher(email).matches()) {
            throw new EtAuthException("Invalid Email format");
        }

        if (userRepository.getCountByEmail(email) > 0){
            throw new EtAuthException("Email already in use");
        }

        Integer idUser = userRepository.create(firstName, lastName, email, password);


        return userRepository.findById(idUser);
    }
}