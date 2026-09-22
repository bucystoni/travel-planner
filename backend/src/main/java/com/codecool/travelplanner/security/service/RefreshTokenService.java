package com.codecool.travelplanner.security.service;

import com.codecool.travelplanner.model.entity.token.RefreshTokenEntity;
import com.codecool.travelplanner.model.entity.user.UserEntity;
import com.codecool.travelplanner.repository.token.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${travelplanner.refresh-token.expiration-ms}")
    private int expirationMs;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(UserEntity user) {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);

        String rawToken = Base64.getUrlEncoder().encodeToString(bytes);
        String tokenHash = hashToken(rawToken);

        Instant createdAt = Instant.now();
        Instant expiresAt = createdAt.plusMillis(expirationMs);

        RefreshTokenEntity tokenEntity = new RefreshTokenEntity(
                tokenHash,
                user,
                expiresAt,
                createdAt
        );

        refreshTokenRepository.save(tokenEntity);

        return rawToken;
    }

    public RefreshTokenEntity validateRefreshToken(String rawToken) {
        String tokenHash = hashToken(rawToken);
        Optional<RefreshTokenEntity> token = refreshTokenRepository.findByTokenHash(tokenHash);

        RefreshTokenEntity refreshToken = token.orElseThrow(() ->
                new IllegalArgumentException("Invalid refresh token"));

        if (refreshToken.getRevokedAt() != null) {
            throw new IllegalArgumentException("Refresh token has been revoked");
        }

        if (refreshToken.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Refresh token has expired");
        }

        return refreshToken;
    }

    private String hashToken(String rawToken) {
        byte[] tokenBytes = rawToken.getBytes(StandardCharsets.UTF_8);

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(tokenBytes);

            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm is not available", e);
        }
    }
}