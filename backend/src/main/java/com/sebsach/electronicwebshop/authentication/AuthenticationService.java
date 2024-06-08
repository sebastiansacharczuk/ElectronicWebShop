package com.sebsach.electronicwebshop.authentication;

import com.sebsach.electronicwebshop.security.jwt.JwtService;
import com.sebsach.electronicwebshop.dto.User;
import com.sebsach.electronicwebshop.repository.RoleRepository;
import com.sebsach.electronicwebshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;




    public void register(RegistrationRequest request){
        var userRole = roleRepository.findByName("USER")
                // TODO( better exception handling )
                .orElseThrow(() -> new IllegalStateException("Role USER was not initiated"));
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .roles(Set.of(userRole))
                .build();
        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var claims = new HashMap<String, Object>();
            var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        catch (Exception e) {
            return AuthenticationResponse.builder()
                    .token("")
                    .build();
        }
    }
}
