package com.wingbank.core.config;

import com.wingbank.core.security.entity.Role;
import com.wingbank.core.security.entity.User;
import com.wingbank.core.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist
        if (userRepository.count() == 0) {
            createSampleUsers();
        }
    }

    private void createSampleUsers() {
        // Create Admin User
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@example.com");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        // Create Regular Users
        User john = new User();
        john.setUsername("john");
        john.setPassword(passwordEncoder.encode("john123"));
        john.setEmail("john@example.com");
        john.setRole(Role.USER);
        userRepository.save(john);

        User jane = new User();
        jane.setUsername("jane");
        jane.setPassword(passwordEncoder.encode("jane123"));
        jane.setEmail("jane@example.com");
        jane.setRole(Role.USER);
        userRepository.save(jane);

        User bob = new User();
        bob.setUsername("bob");
        bob.setPassword(passwordEncoder.encode("bob123"));
        bob.setEmail("bob@example.com");
        bob.setRole(Role.USER);
        userRepository.save(bob);

        User alice = new User();
        alice.setUsername("alice");
        alice.setPassword(passwordEncoder.encode("alice123"));
        alice.setEmail("alice@example.com");
        alice.setRole(Role.USER);
        userRepository.save(alice);

        // Create another Admin
        User superadmin = new User();
        superadmin.setUsername("superadmin");
        superadmin.setPassword(passwordEncoder.encode("super123"));
        superadmin.setEmail("superadmin@example.com");
        superadmin.setRole(Role.ADMIN);
        userRepository.save(superadmin);

        System.out.println("Sample users created:");
        System.out.println("Admin users: admin/admin123, superadmin/super123");
        System.out.println("Regular users: john/john123, jane/jane123, bob/bob123, alice/alice123");
    }
}
