package com.orientbits.blogappapis.controllers;

import com.orientbits.blogappapis.security.JwtAuthRequest;
import com.orientbits.blogappapis.security.JwtAuthResponse;
import com.orientbits.blogappapis.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
        System.out.println("Controller running........");

        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword()));
            System.out.println("Authenticated....");
        }catch (UsernameNotFoundException | BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Bad Credential");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        System.out.println("UserDetails: "+userDetails.getUsername());
        System.out.println("UserDetails: "+userDetails.getPassword());

        System.out.println("USER DETAILS: "+userDetails);

        String generateToken = jwtTokenHelper.generateToken(userDetails);
        System.out.println("Generated Token: "+generateToken);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(generateToken);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
