package org.bsuir.jwtproject.config;

import org.bsuir.jwtproject.model.User;
import org.bsuir.jwtproject.model.enums.Role;
import org.bsuir.jwtproject.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig {

    @Bean
    public ApplicationRunner dataLoader(
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(
                        new User(1L, "admin", passwordEncoder.encode("admin"), Role.ROLE_ADMIN));
            } else if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(
                        new User(2L, "user", passwordEncoder.encode("user"), Role.ROLE_USER));
            }
        };
    }
}
