package com.example.demo.domain.Users.services;

import com.example.demo.domain.Users.models.Users;
import com.example.demo.domain.core.exceptions.ResourceCreationException;
import com.example.demo.domain.core.exceptions.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    Users createUser(Users users) throws ResourceCreationException;
    Users getUserById(Integer id) throws ResourceNotFoundException;

    Users getUserByUid(String uid) throws ResourceNotFoundException;

    List<Users> getAll();
    Users updateUser(Integer id, Users usersDetails) throws ResourceNotFoundException;
    Boolean deleteUser(Integer id) throws ResourceNotFoundException;
}