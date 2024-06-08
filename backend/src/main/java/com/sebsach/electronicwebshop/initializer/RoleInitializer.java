//package com.sebsach.electronicwebshop.initializer;
//
//import com.sebsach.electronicwebshop.dto.Role;
//import com.sebsach.electronicwebshop.role.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RoleInitializer implements ApplicationRunner {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // Check if roles already exist
//        if (roleRepository.findByName("USER").isEmpty()) {
//            Role userRole = new Role();
//            userRole.setName("USER");
//            roleRepository.save(userRole);
//        }
//
//        if (roleRepository.findByName("ADMIN").isEmpty()) {
//            Role adminRole = new Role();
//            adminRole.setName("ADMIN");
//            roleRepository.save(adminRole);
//        }
//    }
//}