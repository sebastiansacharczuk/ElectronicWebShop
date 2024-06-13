package com.sebsach.electronicwebshop.user;

import com.sebsach.electronicwebshop.repository.RoleRepository;
import com.sebsach.electronicwebshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User getCurrentUser(Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        return userRepository.findByUsername(user.getUsername()).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}