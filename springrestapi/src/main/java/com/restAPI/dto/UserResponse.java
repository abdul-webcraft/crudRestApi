package com.restAPI.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
}
