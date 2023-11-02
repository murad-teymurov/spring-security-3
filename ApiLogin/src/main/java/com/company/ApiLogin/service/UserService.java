package com.company.ApiLogin.service;

import com.company.ApiLogin.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
//    private final UserRepository userRepository

    private static final String EXISTING_EMAIL = "test@gmail.com";

    public Optional<UserEntity> findByEmail(String email) {
        // TODO: Move this to a DB
        if(!EXISTING_EMAIL.equalsIgnoreCase(email)) return Optional.empty();

        var user = new UserEntity();
        user.setId(1L);
        user.setEmail(EXISTING_EMAIL);
        user.setPassword("$2a$12$Gl/D56bxdu8kj4Z/r.d7VesaBr19NKrbY8Blu.ntbl6vSit/035Nu"); //test
        user.setRole("ROLE_ADMIN");
        user.setExtraInfo(" admin ");
        return Optional.of(user);
    }
}
