package com.restAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restAPI.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private int id;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City must contain only letters")
    @NotBlank(message = "City is required")
    @Size(min = 5, message = "City must be atLeast 5 character")
    private String city;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State must contain only letters")
    @NotBlank(message = "State is required")
    @Size(min = 5, message = "State must be atLeast 5 character")
    private String state;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Country must contain only letters")
    @NotBlank(message = "Country is required")
    @Size(min = 5, message = "Country must be atLeast 5 character")
    private String country;


    private UserRequestDTO user;
}
