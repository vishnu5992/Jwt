package com.auth.jwt.auth;


import com.auth.jwt.payload.LoginRequest;
import com.auth.jwt.payload.SignuUpRequest;
import com.auth.jwt.response.BasicResponse;
import com.auth.jwt.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthorizationService authorizationService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        BasicResponse<?> basicResponse = authorizationService.authenticateUser(loginRequest);
        return new ResponseEntity<>(basicResponse, HttpStatus.valueOf(basicResponse.getStatusCode()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignuUpRequest signUpRequest) {
        BasicResponse<?> basicResponse = authorizationService.registerUser(signUpRequest);
        return new ResponseEntity<>(basicResponse, HttpStatus.valueOf(basicResponse.getStatusCode()));
    }


}
