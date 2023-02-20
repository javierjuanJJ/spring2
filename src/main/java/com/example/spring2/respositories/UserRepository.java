package com.example.spring2.respositories;

import com.example.spring2.Exceptions.EtAuthException;
import com.example.spring2.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;


public interface UserRepository {
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;
    User findByEmailAndPassword(String email, String password) throws EtAuthException;
    Integer getCountByEmail(String email);
    User findById(Integer userId);
}
