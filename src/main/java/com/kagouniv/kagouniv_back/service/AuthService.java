package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.auth.service.JwtService;
import com.kagouniv.kagouniv_back.domain.User;
import com.kagouniv.kagouniv_back.dto.UserRequest;
import com.kagouniv.kagouniv_back.exception.ApiException;
import com.kagouniv.kagouniv_back.exception.ErrorDefine;
import com.kagouniv.kagouniv_back.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //== 회원가입 ==//
    public String register(UserRequest userRequest) {
        User user = userRequest.toEntity();

        user.encodePassword(passwordEncoder);
        return userRepository.save(user).getLoginId();
    }

    //== 로그아웃 ==//
    public void logout(HttpServletRequest request) {
        String userName = jwtService.extractUsername(request)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        jwtService.destroyRefreshToken(userName);
        SecurityContextHolder.clearContext();
    }

}
