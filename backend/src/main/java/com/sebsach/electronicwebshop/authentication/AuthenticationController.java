package com.sebsach.electronicwebshop.authentication;

import com.sebsach.electronicwebshop.dto.User;
import com.sebsach.electronicwebshop.security.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

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

    @PostMapping(value = "/sessionValid", produces = "application/json")
    public ResponseEntity<SessionValidResponse> sessionValid(Authentication authentication) {
        User user = null;
        try {
            user = (User) authentication.getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("User is not null: {}", user != null);
        return ResponseEntity.ok(new SessionValidResponse(user != null));
    }


    public static class SessionValidResponse {
        public SessionValidResponse(boolean valid) {
            this.isValid = valid;
        }
        public boolean isValid;
    }


}
