package com.auth.jwt.service;

import com.auth.jwt.model.Role;
import com.auth.jwt.model.User;
import com.auth.jwt.payload.JwtResponse;
import com.auth.jwt.payload.LoginRequest;
import com.auth.jwt.payload.MessageResponse;
import com.auth.jwt.payload.SignuUpRequest;
import com.auth.jwt.repository.RoleRepository;
import com.auth.jwt.repository.UserRepository;
import com.auth.jwt.response.BasicResponse;
import com.auth.jwt.response.ResponseFormatUtilities;
import com.auth.jwt.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorizationService
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public BasicResponse<?> registerUser(SignuUpRequest signUpRequest) {
        BasicResponse<MessageResponse> basicResponse = new BasicResponse<>();

        try {

            User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            String strRoles = signUpRequest.getRole();
            Role role = null;
            if (strRoles != null) {
                role = new Role("User");
                roleRepository.save(role);
            }
            user.setRole(role);
            userRepository.save(user);

            MessageResponse messageResponse = new MessageResponse("User has been added");
            basicResponse.setData(messageResponse);
            basicResponse.setStatusCode(HttpStatus.OK.value());
            basicResponse = (BasicResponse<MessageResponse>) ResponseFormatUtilities.createSuccessResponse(basicResponse, "100", "User has been added!!");
        }catch (Exception e){
            basicResponse = (BasicResponse<MessageResponse>) ResponseFormatUtilities.createErrorResponse(basicResponse, "400", "Expection Occured in adding user");

        }
        return basicResponse;
    }

    public BasicResponse<?> authenticateUser(LoginRequest loginRequest) {
        BasicResponse<JwtResponse> basicResponse = new BasicResponse<>();
        JwtResponse jwtResponse;
        try{

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            User currentUser = userRepository.findByEmailId(userDetails.getEmail(),false);


            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            String accessToken = jwtUtils.generateJwtToken(userDetails);
            jwtResponse = new JwtResponse(accessToken,userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),roles);
            if (jwtResponse != null) {
                basicResponse.setData(jwtResponse);
                basicResponse.setStatusCode(HttpStatus.OK.value());
                basicResponse = (BasicResponse<JwtResponse>) ResponseFormatUtilities.createSuccessResponse(basicResponse,"200","User successfully Authorized");
            } else {
                basicResponse = (BasicResponse<JwtResponse>) ResponseFormatUtilities.createErrorResponse(basicResponse,"400","Unable to authorize user");
            }
        }catch (Exception e){
            basicResponse = (BasicResponse<JwtResponse>) ResponseFormatUtilities.createErrorResponse(basicResponse,"400","Exception Occurred While authorizing user");
        }
        return basicResponse;
    }
}
