package com.auth.jwt.payload;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class SignuUpRequest {

    private String username;

    private String email;

    private String role;

    private String password;
}
