package com.kagouniv.kagouniv_back.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.kagouniv.kagouniv_back.domain.User;
import com.kagouniv.kagouniv_back.exception.ApiException;
import com.kagouniv.kagouniv_back.exception.ErrorDefine;
import com.kagouniv.kagouniv_back.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access.expiration}")
    private long accessTokenValidityInSeconds;
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenValidityInSeconds;
    @Value("${jwt.access.header}")
    private String accessHeader;
    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String USERNAME_CLAIM = "email";
    private static final String BEARER = "Bearer ";

    private final UserRepository userRepository;

    @Override
    public String createAccessToken(String email) {
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenValidityInSeconds * 1000)) //30분
                .withClaim(USERNAME_CLAIM, email)
                .sign(Algorithm.HMAC512(secret));
    }

    @Override
    public String createRefreshToken() {
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenValidityInSeconds * 1000)) //7일
                .sign(Algorithm.HMAC512(secret));
    }

    @Override
    public void updateRefreshToken(String loginId, String refreshToken) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        user.updateRefreshToken(refreshToken);
    }

    @Override
    public void destroyRefreshToken(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        user.destroyRefreshToken();
    }

    /**
     * response set ContentType, Status, AccessToken, RefreshToken
     */
    @Override
    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);

        log.info("JwtService.sendAccessAndRefreshToken {}", accessToken);
    }

    @Override
    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
    }

    //== 4 ==//
    public Optional<String> extractUsername(HttpServletRequest request) throws TokenExpiredException{
        String accessToken = extractAccessToken(request)
                .orElseThrow(() -> new ApiException(ErrorDefine.TOKEN_NOT_FOUND));

        return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secret))
                .build().verify(accessToken).getClaim(USERNAME_CLAIM).asString());
    }

    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(accessToken -> accessToken.startsWith(BEARER))
                .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    private void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, accessToken);
    }

    private void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
    }

    /* 토큰 유효성 검증 */
    public boolean isValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
