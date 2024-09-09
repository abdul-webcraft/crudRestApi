package com.restAPI.service;

import com.restAPI.dto.UserDTO;
import com.restAPI.model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);

    public User updateUser(Integer id,User user);

    public User getUserById(Integer id);

    public UserDTO getUserByName(String name);

    public List<UserDTO> getAllUser();

    public List<User> getUsers();

    public String deleteUser(Integer id);

}
