package com.restAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required!")
    private String name;
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 5,message = "Password must be atLeast 5 character")
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Address is required")
    private String address;

}
