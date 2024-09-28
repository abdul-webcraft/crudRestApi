package com.restAPI.repository;

import com.restAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User>  findByName(String name);
    boolean existsByEmail(String email);
}
