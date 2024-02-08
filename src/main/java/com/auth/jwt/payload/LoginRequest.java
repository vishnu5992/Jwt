package com.auth.jwt.payload;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class LoginRequest {

    private String email;
    private String password;
}
