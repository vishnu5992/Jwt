package com.auth.jwt.response;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class BasicResponse<T> implements Serializable {

    private ErrorResponse error;
    private SuccessResponse message;
    private T data;
    private Boolean status;
    private Integer statusCode;
}
