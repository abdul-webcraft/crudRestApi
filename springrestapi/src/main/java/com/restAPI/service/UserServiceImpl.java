package com.restAPI.service;

import com.restAPI.dto.UserDTO;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.model.User;
import com.restAPI.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
       if(!userRepository.existsById(id)){
           throw new ResourceNotFoundException("User not existed with given id :" + id);
       }
       user.setId(id);
       return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not existed with given id :" + id));

        return user;
    }

    @Override
    public UserDTO getUserByName(String name) {
        User user = userRepository.findByName(name);
        if(user!=null){
            return modelMapper.map(user, UserDTO.class);
        }
        throw new ResourceNotFoundException("User not existed with given name :" + name);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(Integer id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "Deleted Successfully";
        }else{
            return "failure";
        }


    }
}
