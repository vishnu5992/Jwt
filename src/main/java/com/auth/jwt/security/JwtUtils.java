package com.auth.jwt.security;


import com.auth.jwt.service.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${heptagon.app.jwtSecret}")
    private String jwtSecret;

    @Value("${heptagon.app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public boolean validateJwtToken(String authToken) {

        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        } catch (Exception e) {
            log.info("JWT token is expired: {}", e.getMessage());
        }
        return (claimsJws != null) ? true : false;
    }

    public String generateTokenFromUserEmail(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public String getUserEmailFromJwtToken(String token) {

        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public String generateJwtToken(UserDetailsImpl userDetails) {
        return generateTokenFromUserEmail(userDetails.getEmail());

    }
}

