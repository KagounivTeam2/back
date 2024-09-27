package com.kagouniv.kagouniv_back.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public interface JwtService {

    String createAccessToken(String email);

    String createRefreshToken();

    void updateRefreshToken(String loginId, String refreshToken);

    void destroyRefreshToken(String loginId);

    void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken);

    void sendAccessToken(HttpServletResponse response, String accessToken);

    Optional<String> extractUsername(HttpServletRequest request);

    Optional<String> extractAccessToken(HttpServletRequest request);

    Optional<String> extractRefreshToken(HttpServletRequest request);

    boolean isValid(String token);
}
