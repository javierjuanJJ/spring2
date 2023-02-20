package com.example.spring2.services;

import com.example.spring2.Exceptions.EtAuthException;
import com.example.spring2.domain.User;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
