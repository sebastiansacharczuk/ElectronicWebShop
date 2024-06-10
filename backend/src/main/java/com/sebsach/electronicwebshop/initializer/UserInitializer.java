package com.sebsach.electronicwebshop.initializer;

import com.sebsach.electronicwebshop.dto.Role;
import com.sebsach.electronicwebshop.dto.User;
import com.sebsach.electronicwebshop.repository.RoleRepository;
import com.sebsach.electronicwebshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.util.Pair;
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
    @Override
    public void run(ApplicationArguments args) throws Exception {

        roleInitializer.run();
        List<Pair<String, String>> pairs = new ArrayList<>();
        pairs.add(Pair.of("user123", "USER"));
        pairs.add(Pair.of("admin123", "ADMIN"));

        for (Pair<String, String> pair : pairs) {
            var userRole = roleRepository.findByName(pair.getSecond())
                    .orElseThrow(() -> new IllegalStateException("User initialization failed due to lack of role " + pair.getSecond()));
            User user = User.builder()
                    .username(pair.getFirst())
                    .password(passwordEncoder.encode(pair.getFirst()))
                    .accountLocked(false)
                    .roles(Set.of(userRole))
                    .build();
            userRepository.save(user);
        }
    }
}
