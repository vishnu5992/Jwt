package com.auth.jwt.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ErrorResponse implements Serializable
{

    private String errorCode;
    private String errorMessage;
    private List<String> errorDetails;
}
