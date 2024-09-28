package com.restAPI.dto;


import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserRequestDTO {

    private int id;
    @NotBlank(message = "Name is required!")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;


    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be valid")
    @Size(min = 10, message = "Email must be at least 10 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{5,}$", message = "Password must be at least 5 characters long and include both letters and numbers")
    private String password;


    @Valid
    private List<AddressRequest> addresses;
}
