package com.sebsach.electronicwebshop.authentication;

import com.sebsach.electronicwebshop.cart.Cart;
import com.sebsach.electronicwebshop.repository.CartRepository;
import com.sebsach.electronicwebshop.security.jwt.JwtService;
import com.sebsach.electronicwebshop.user.User;
import com.sebsach.electronicwebshop.repository.RoleRepository;
import com.sebsach.electronicwebshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CartRepository cartRepository;

    public void register(RegistrationRequest request){
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER was not initiated"));
        if(userRepository.findByUsername(request.getUsername()).isEmpty()){
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setCart(new Cart());
            user.setRoles(Set.of(userRole));
            user.setAccountLocked(false);
//            User user = User.builder()
//                    .username(request.getUsername())
//                    .password(passwordEncoder.encode(request.getPassword()))
//                    .cart(new Cart())
//                    .accountLocked(false)
//                    .roles(Set.of(userRole))
//                    .build();
            userRepository.save(user);
        }
        else {
            throw new BadCredentialsException("Username already in use");
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

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
}
