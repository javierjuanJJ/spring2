package com.example.spring2.services;

import com.example.spring2.Exceptions.EtAuthException;
import com.example.spring2.domain.User;
import com.example.spring2.respositories.UserRepository;
import com.example.spring2.respositories.UserRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
        public User validateUser(String email, String password) throws EtAuthException {
            if(email != null) email = email.toLowerCase();
            return userRepository.findByEmailAndPassword(email, password);
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
