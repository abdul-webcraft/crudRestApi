package com.restAPI.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AddressResponse {

    private int id;
    private String city;
    private String state;
    private String country;

}
