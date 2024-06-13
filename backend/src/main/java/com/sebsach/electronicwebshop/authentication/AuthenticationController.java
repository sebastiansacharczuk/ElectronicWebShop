package com.sebsach.electronicwebshop.authentication;

import com.sebsach.electronicwebshop.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private static final Logger logger = LoggerFactory.getLogger(Boolean.class);

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ){
        authService.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
