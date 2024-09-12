package com.restAPI.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name is required!")
    @Size(min = 3,message = "Password must be atLeast 3 character")
    private String name;
    @NotBlank(message = "Email is required")
    @Size(min = 10,message = "Password must be atLeast 10 character")
    private String email;
    @Size(min = 5,message = "Password must be atLeast 5 character")
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Address is required")
    @Size(min = 5,message = "Password must be atLeast 5 character")
    private String address;
}
