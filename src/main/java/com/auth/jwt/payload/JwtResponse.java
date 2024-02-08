package com.auth.jwt.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class JwtResponse
{
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Integer id;
    private String username;
    private String email;
    private List<String> role;

    public JwtResponse(String token, Integer id, String username, String email, List<String> role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }


}
