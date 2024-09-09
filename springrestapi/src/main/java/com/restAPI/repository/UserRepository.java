package com.restAPI.repository;

import com.restAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

    public User findByName(String name);
}
