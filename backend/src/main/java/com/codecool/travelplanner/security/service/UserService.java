package com.codecool.travelplanner.security.service;

import com.codecool.travelplanner.model.entity.user.Role;
import com.codecool.travelplanner.model.entity.user.UserEntity;
import com.codecool.travelplanner.model.UserRequest;
import com.codecool.travelplanner.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void registerUser(UserRequest request) {
        if (userRepository.findUserByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException(format("Username %s is already taken", request.getUsername()));
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(encoder.encode(request.getPassword()));
        userEntity.setRoles(Set.of(Role.ROLE_USER));

        userRepository.save(userEntity);
    }
}
