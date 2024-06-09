package com.sebsach.electronicwebshop.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

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
