package com.restAPI.responseModel;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private int status;
    private String message;
    private String response;
    private T data;
}
