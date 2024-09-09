package com.restAPI.controller;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.restAPI.dto.UserDTO;
import com.restAPI.exception.ResourceNotFoundException;
import com.restAPI.model.User;
import com.restAPI.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/")
public class RestAPIController {

    @Autowired
    private UserService userService;

    @GetMapping("admin/users")
    public ResponseEntity<List<User>> GetUsers(){
        List<User> allUser = userService.getUsers();
        if(!allUser.isEmpty()){
            return new ResponseEntity<>(allUser,HttpStatus.OK);
        }
        throw new ResourceNotFoundException("User are not existed");

    }

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> GetAllUser(){
        List<UserDTO> allUser = userService.getAllUser();
        if(!allUser.isEmpty()){
            return new ResponseEntity<>(allUser,HttpStatus.OK);
        }
       throw new ResourceNotFoundException("User are not existed");

    }

    @GetMapping("admin/user/{id}")
    public ResponseEntity<User> user(@PathVariable int id){
        User user = userService.getUserById(id);
        log.debug("super admin user {}",id);
        if(user==null){
            throw new ResourceNotFoundException("User not exits with id :"+id);
        }else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("user/{name}")
    public ResponseEntity<UserDTO> userByName(@PathVariable String name){
        UserDTO userDTO = userService.getUserByName(name);
        if(userDTO==null){
            throw new ResourceNotFoundException("User not exits with name :"+name);
        }else {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
    }

    @PostMapping("admin/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user, BindingResult result){

        if(result.hasErrors()){
            StringBuilder errors=new StringBuilder();
            result.getAllErrors().forEach(error->{
                String fieldName=((FieldError)error).getField();
                String errorMessage=error.getDefaultMessage();
                errors.append(fieldName).append(": ").append(errorMessage).append("; /n");
            });
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }

        User saveUser = userService.createUser(user);
        return new ResponseEntity<>("User created successfully..", HttpStatus.CREATED);

    }

    @PutMapping("admin/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id,@Valid @RequestBody User user,BindingResult result ){
        if(result.hasErrors()){
            StringBuilder errors=new StringBuilder();
            result.getAllErrors().forEach(error->{
                String fieldName=((FieldError)error).getField();
                String errorMessage=error.getDefaultMessage();
                errors.append(fieldName).append(": ").append(errorMessage).append(";/n ");
            });
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        }
            User updatedUser = userService.updateUser(id,user);
            return new ResponseEntity<>("user updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("admin/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        String status = userService.deleteUser(id);
        if(status.equals("Deleted Successfully")){
            return new ResponseEntity<>(status,HttpStatus.OK);
        }
       throw new ResourceNotFoundException("User not existed with given id :"+id);
    }

}
