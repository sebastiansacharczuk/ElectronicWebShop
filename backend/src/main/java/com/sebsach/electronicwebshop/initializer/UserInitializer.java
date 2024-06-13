package com.sebsach.electronicwebshop.initializer;

import com.sebsach.electronicwebshop.authentication.AuthenticationService;
import com.sebsach.electronicwebshop.authentication.RegistrationRequest;
import com.sebsach.electronicwebshop.user.User;
import com.sebsach.electronicwebshop.repository.RoleRepository;
import com.sebsach.electronicwebshop.repository.UserRepository;
import com.sebsach.electronicwebshop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserInitializer implements ApplicationRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleInitializer roleInitializer;
    private final AuthenticationService authenticationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        roleInitializer.run();
        List<Pair<String, String>> pairs = new ArrayList<>();
        pairs.add(Pair.of("user123", "USER"));
        pairs.add(Pair.of("admin123", "ADMIN"));

        for (Pair<String, String> pair : pairs) {
            var userRole = roleRepository.findByName(pair.getSecond())
                    .orElseThrow(() -> new IllegalStateException("User initialization failed due to lack of role " + pair.getSecond()));
            authenticationService.register(new RegistrationRequest(pair.getFirst(), pair.getFirst()));

            User user = userRepository.findByUsername("admin123").orElse( null );
            if (user != null) {
                user.setRoles(Set.of(userRole));
                userRepository.save(user);
            }
        }
    }
}
