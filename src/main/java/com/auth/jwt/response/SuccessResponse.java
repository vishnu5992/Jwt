package com.auth.jwt.response;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SuccessResponse implements Serializable
{

    private String messageCode;
    private String messageDescription;
}
