package com.restAPI.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserResponseDTO {

    private int id;
    private String name;
    private String email;
    private String password;
    private List<AddressResponse> addresses;


}
