package org.bsuir.jwtproject.config;

import lombok.AllArgsConstructor;
import org.bsuir.jwtproject.repository.UserRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class WebConfig {
    private final UserRepository userRepository;
}
