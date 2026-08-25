package com.codecool.travelplanner.security.service;

import com.codecool.travelplanner.model.AuthResponse;
import com.codecool.travelplanner.model.entity.user.Role;
import com.codecool.travelplanner.model.entity.user.UserEntity;
import com.codecool.travelplanner.model.UserRequest;
import com.codecool.travelplanner.repository.user.UserRepository;
import com.codecool.travelplanner.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    public AuthService(
            UserRepository userRepository,
            PasswordEncoder encoder,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

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

    private Authentication authenticateUser(UserRequest request) {
        Optional<UserEntity> user;

        if (request.getUsername().equals("") || request.getUsername() == null) {
            user = userRepository.findUserByEmail(request.getEmail());
        } else {
            user = userRepository.findUserByUsername(request.getUsername());
        }

        if (user.isEmpty()) throw new UsernameNotFoundException("Invalid credentials");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.get().getUsername(), request.getPassword());
        return authenticationManager.authenticate(token);
    }

    public AuthResponse login(UserRequest request) {
        Authentication authentication = authenticateUser(request);
        String token = jwtUtils.generateJwtToken(authentication);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .toList();

        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setUsername(authentication.getName());
        response.setRoles(roles);

        return response;
    }

}
